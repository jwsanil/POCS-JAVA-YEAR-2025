package com.example.camelrouter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static java.lang.ProcessBuilder.Redirect.to;

@Component
public class EndpointRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        errorHandler(deadLetterChannel("jms:queue:deadLetterQueue")
                .maximumRedeliveries(2)       // Retry 2 times
                .redeliveryDelay(1000)        // Wait 1 second between retries
                .retryAttemptedLogLevel(org.apache.camel.LoggingLevel.WARN)
        );


        // ------------------------
        // Timer-based scheduled route
        // ------------------------
       /* from("timer://endpointTimer?period=5000") // every 5 seconds
                .routeId("endpointTimerRoute")
                .log("Timer triggered at ${header.CamelTimerFiredTime}")
                .setBody(simple("Scheduled message at ${header.CamelTimerFiredTime}"))
                .to("direct:scheduledTask");
*/
        // ------------------------
        // Endpoint that receives the timer message
        // ------------------------
        from("direct:scheduledTask")
                .routeId("scheduledTaskRoute")
                .log("Processing scheduled message: ${body}")
                .transform().simple("Processed scheduled message: ${body}");



                // New SEDA asynchronous route
                // ------------------------
                from("seda:asyncQueue?concurrentConsumers=3")
                        .routeId("sedaRoute")
                        .log("Processing message from SEDA queue: ${body}")
                        .transform().simple("Processed asynchronously: ${body}");

// ------------------------
// File Route: Reads messages from 'input' folder
// ------------------------
        from("file:input?noop=true") // Reads files but doesn’t delete
                .routeId("fileRoute")
                .log("File received: ${header.CamelFileName}")
                .convertBodyTo(String.class)
                .log("File content: ${body}")
                .to("seda:fileQueue"); // New separate SEDA queue for file processing

// ------------------------
// File Processing SEDA queue
// ------------------------
        from("seda:fileQueue?concurrentConsumers=2")
                .routeId("fileProcessingRoute")
                .log("Processing file asynchronously: ${body}")
                .process(exchange -> {
                    String content = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("Processed file content: " + content);
                })
                .log("File processed result: ${body}")
         .to("file:output?fileName=${header.CamelFileName}");

// ------------------------

/*
        // Manual trigger entry
        from("direct:ftpRoute")
                .routeId("ftpRouteManual")
                .to("ftp://username:password@localhost:21/inputFolder?noop=true&binary=true");

        // Automatic FTP polling
        from("ftp://username:password@localhost:21/inputFolder?noop=true&binary=true")
                .routeId("ftpRouteAuto")
                .log("FTP file received: ${header.CamelFileName}")
                .convertBodyTo(String.class)
                .to("seda:ftpQueue");

        // FTP processing SEDA queue
        from("seda:ftpQueue?concurrentConsumers=2")
                .routeId("ftpProcessingRoute")
                .log("Processing FTP file asynchronously: ${body}")
                .process(exchange -> {
                    String content = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("Processed FTP file content: " + content);
                })
                .to("file:output/ftpFiles")
                .log("FTP file processed and written to output folder: ${header.CamelFileName}");


*/

        // ------------------------
// Manual trigger entry (simulated FTP)
// ------------------------
        from("direct:ftpRoute")
                .routeId("ftpRouteManual")
                .to("file://dummyFtp?noop=true"); // writes a file to dummyFtp folder

// ------------------------
// Automatic "FTP" polling (simulated using file folder)
// ------------------------
        from("file://dummyFtp?noop=true&delay=5000")
                .routeId("ftpRouteAuto")
                .log("Simulated FTP file received: ${header.CamelFileName}")
                .convertBodyTo(String.class)
                .to("seda:ftpQueue");

// ------------------------
// FTP processing SEDA queue
// ------------------------
        from("seda:ftpQueue?concurrentConsumers=2")
                .routeId("ftpProcessingRoute")
                .log("Processing simulated FTP file asynchronously: ${body}")
                .process(exchange -> {
                    String content = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("Processed FTP file content: " + content);
                })
                .to("file://output/ftpFiles")
                .log("Processed file written to output folder: ${header.CamelFileName}");




        // ------------------------
// Manual JMS trigger route
// ------------------------
        from("direct:jmsRoute")
                .routeId("jmsManualRoute")
                .log("Sending message to JMS queue: ${body}")
                .to("jms:queue:exampleQueue");

// ------------------------
// JMS consumer route
/*// ------------------------
        from("jms:queue:exampleQueue")// disabling temportarily to enable /read/api
                .routeId("jmsConsumerRoute")
                .log("Received message from JMS queue: ${body}")
                .process(exchange -> {
                    String message = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("Processed JMS message: " + message);
                })
                .log("Processed JMS message: ${body}");


*/

        // ------------------------
        // Manual Kafka trigger route
        // ------------------------
       /* from("direct:kafkaRoute")
                .routeId("kafkaManualRoute")
                .log("Sending message to Kafka topic: ${body}")
                .to("kafka:exampleTopic?brokers=localhost:9092");


*/

        // ------------------------
        // ActiveMQ Dead Letter Channel
        // ------------------------
        from("jms:queue:exampleQueue")
                .routeId("jmsConsumerRoute")
                .log("Received message from JMS queue: ${body}")
                .process(exchange -> {
                    String message = exchange.getIn().getBody(String.class);
                    // Simulate failure if message contains "fail"
                    if (message.contains("fail")) {
                        throw new RuntimeException("Processing failed for message: " + message);
                    }
                    exchange.getIn().setBody("Processed JMS message: " + message);
                })
                .log("Processed JMS message: ${body}");


    }








}
