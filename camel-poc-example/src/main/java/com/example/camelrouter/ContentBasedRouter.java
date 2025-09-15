package com.example.camelrouter;

import com.example.camelrouter.bean.DynamicRouterBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ContentBasedRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
            .choice()
                .when(simple("${body} contains 'order'"))
                    .to("log:order?showAll=true")
                .when(simple("${body} contains 'invoice'"))
                    .to("log:invoice?showAll=true")
                .otherwise()
                    .log("othr: ${body}");


        from("direct:recipientListExample")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);

                    // Dynamically compute recipients based on message content
                    List<String> recipients;
                    if (body.contains("urgent")) {
                        recipients = Arrays.asList("direct:teamA", "direct:teamB");
                    } else {
                        recipients = Arrays.asList("direct:teamC", "direct:teamD");
                    }

                    // Set recipients in header
                    exchange.getIn().setHeader("recipients", recipients);
                })
                .recipientList(header("recipients"))
                .parallelProcessing()
                .aggregationStrategy((oldEx, newEx) -> {
                    // Merge bodies from all recipients
                    if (oldEx == null) return newEx;
                    String merged = oldEx.getIn().getBody(String.class) + " | " + newEx.getIn().getBody(String.class);
                    oldEx.getIn().setBody(merged);
                    return oldEx;
                })
                .log("Merged result from all recipients: ${body}");


// ------------------------
// Recipient routes (direct endpoints)
// ------------------------
        from("direct:teamA")
                .log("Team A received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by Team A]");
                });

        from("direct:teamB")
                .log("Team B received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by Team B]");
                });

        from("direct:teamC")
                .log("Team C received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by Team C]");
                });

        from("direct:teamD")
                .log("Team D received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by Team D]");
                });



        // ------------------------
// Splitter Route
// ------------------------
        from("direct:splitExample")
                .routeId("splitterRoute")
                .log("Original message: ${body}")
                // Split by comma and send each piece separately
                .split(body().tokenize(","))
                .log("Split piece: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class).trim();
                    exchange.getIn().setBody(msg + " [processed by Splitter]");
                })
                .end()
                .log("All pieces processed for message: ${body}");



        // ------------------------
// Multicast Route with Aggregation
// ------------------------
        from("direct:multicastExample")
                .routeId("multicastRoute")
                .log("Original message: ${body}")
                .multicast()
                .parallelProcessing() // optional: process in parallel
                .aggregationStrategy((oldEx, newEx) -> {
                    if (oldEx == null) return newEx;
                    String merged = oldEx.getIn().getBody(String.class) + " | " + newEx.getIn().getBody(String.class);
                    oldEx.getIn().setBody(merged);
                    return oldEx;
                })
                .to("direct:multiA", "direct:multiB")
                .end()
                .log("Multicast aggregated result: ${body}");

// ------------------------
// Multicast recipient routes
// ------------------------
        from("direct:multiA")
                .log("MultiA received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by MultiA]");
                });

        from("direct:multiB")
                .log("MultiB received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by MultiB]");
                });



        // ------------------------
// Routing Slip Route
// ------------------------
        from("direct:routingSlipExample")
                .routeId("routingSlipRoute")
                .log("Original message: ${body}")
                // Dynamically set the route list in a header
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    List<String> routes;

                    if (body.contains("step1")) {
                        routes = Arrays.asList("direct:stepA", "direct:stepB");
                    } else {
                        routes = Arrays.asList("direct:stepB", "direct:stepC");
                    }

                    exchange.getIn().setHeader("myRoutingSlip", routes);
                })
                // Routing Slip uses the header to dynamically route the message
                .routingSlip(header("myRoutingSlip"))
                .log("Routing Slip processing completed for message: ${body}");

// ------------------------
// Routing Slip steps
// ------------------------
        from("direct:stepA")
                .log("Step A received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by StepA]");
                });

        from("direct:stepB")
                .log("Step B received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by StepB]");
                });

        from("direct:stepC")
                .log("Step C received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by StepC]");
                });



// ------------------------
// Dynamic Router Route
// ------------------------
        from("direct:dynamicRouterExample")
                .routeId("dynamicRouterRoute")
                .log("Original message: ${body}")
                .dynamicRouter(method(DynamicRouterBean.class, "route"))
                .log("Dynamic Router processing completed: ${body}");

// ------------------------
// Dynamic Router endpoints
// ------------------------
        from("direct:dynA")
                .log("Dynamic A received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by DynA]");
                });

        from("direct:dynB")
                .log("Dynamic B received: ${body}")
                .process(exchange -> {
                    String msg = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(msg + " [processed by DynB]");
                });










    }






}
