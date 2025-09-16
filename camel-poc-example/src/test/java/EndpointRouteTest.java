

import com.example.camelrouter.EndpointRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndpointRouteTest {

    private CamelContext camelContext;
    private ProducerTemplate template;

    @BeforeEach
    void setup() throws Exception {
        camelContext = new DefaultCamelContext();

        // Add the routes, but override DLQ with mock
        camelContext.addRoutes(new EndpointRoute() {
            @Override
            public void configure() throws Exception {
                // Use Mock for DLQ in tests
                errorHandler(deadLetterChannel("mock:deadLetterQueue")
                        .maximumRedeliveries(2)
                        .redeliveryDelay(1000)
                );

                // ScheduledTask route
                from("direct:scheduledTask")
                        .routeId("scheduledTaskRoute")
                        .transform().simple("Processed scheduled message: ${body}")
                        .to("mock:outQueue");

                // SEDA asynchronous route
                from("seda:asyncQueue?concurrentConsumers=3")
                        .routeId("sedaRoute")
                        .transform().simple("Processed asynchronously: ${body}")
                        .to("mock:asyncOut");

                // File route -> SEDA file processing
                from("file:input?noop=true")
                        .routeId("fileRoute")
                        .convertBodyTo(String.class)
                        .to("seda:fileQueue");

                from("seda:fileQueue?concurrentConsumers=2")
                        .routeId("fileProcessingRoute")
                        .process(exchange -> {
                            String content = exchange.getIn().getBody(String.class);
                            exchange.getIn().setBody("Processed file content: " + content);
                        })
                        .to("mock:fileOut");

                // Manual JMS route
                from("direct:jmsRoute")
                        .routeId("jmsManualRoute")
                        .to("seda:jmsQueue");

                // JMS consumer route
                from("seda:jmsQueue")
                        .routeId("jmsConsumerRoute")
                        .process(exchange -> {
                            String message = exchange.getIn().getBody(String.class);
                            if (message.contains("fail")) {
                                throw new RuntimeException("Processing failed for message: " + message);
                            }
                            exchange.getIn().setBody("Processed JMS message: " + message);
                        })
                        .to("mock:jmsProcessed");
            }
        });

        camelContext.start();
        template = camelContext.createProducerTemplate();
    }

    @Test
    void testScheduledTaskRoute() throws Exception {
        MockEndpoint mockOut = camelContext.getEndpoint("mock:outQueue", MockEndpoint.class);
        mockOut.expectedBodiesReceived("Processed scheduled message: Hello");

        template.sendBody("direct:scheduledTask", "Hello");

        mockOut.assertIsSatisfied();
    }

    @Test
    void testJmsSuccess() throws Exception {
        MockEndpoint mockProcessed = camelContext.getEndpoint("mock:jmsProcessed", MockEndpoint.class);
        mockProcessed.expectedBodiesReceived("Processed JMS message: Hello JMS");

        template.sendBody("direct:jmsRoute", "Hello JMS");

        mockProcessed.assertIsSatisfied();
    }

    @Test
    void testJmsDeadLetter() throws Exception {
        MockEndpoint mockDLQ = camelContext.getEndpoint("mock:deadLetterQueue", MockEndpoint.class);
        mockDLQ.expectedMessageCount(1);

        // Send a message that will fail
        template.sendBody("direct:jmsRoute", "fail message");

        mockDLQ.assertIsSatisfied();
    }

    @Test
    void testAsyncSedaRoute() throws Exception {
        MockEndpoint mockAsync = camelContext.getEndpoint("mock:asyncOut", MockEndpoint.class);
        mockAsync.expectedBodiesReceived("Processed asynchronously: Async Test");

        template.sendBody("seda:asyncQueue", "Async Test");

        mockAsync.assertIsSatisfied();
    }

    @Test
    void testFileProcessingRoute() throws Exception {
        MockEndpoint mockFile = camelContext.getEndpoint("mock:fileOut", MockEndpoint.class);
        mockFile.expectedBodiesReceived("Processed file content: Test File Content");

        template.sendBody("seda:fileQueue", "Test File Content");

        mockFile.assertIsSatisfied();
    }
}
