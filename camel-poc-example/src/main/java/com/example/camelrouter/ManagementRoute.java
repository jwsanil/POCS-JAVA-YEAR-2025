package com.example.camelrouter;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class ManagementRoute extends RouteBuilder {

    // Cache to hold processed results
    private final Map<String, String> resultCache = new ConcurrentHashMap<>();

    // Idempotent repository to detect duplicates
    private final MemoryIdempotentRepository repo = new MemoryIdempotentRepository();

    @Override
    public void configure() throws Exception {

        from("direct:idempotent")
                .routeId("idempotentConsumerRoute")
                .log("Incoming message: ${body}")

                // Idempotent consumer to avoid duplicate processing
                .idempotentConsumer(simple("${body}"), repo)
                .skipDuplicate(false) // don’t drop duplicates, let us handle them
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);

                    if (resultCache.containsKey(body)) {
                        // Duplicate → return cached result
                        exchange.getIn().setBody("From cache: " + resultCache.get(body));
                    } else {
                        // New → process and store in cache
                        String processed = "Processed unique message: " + body;
                        resultCache.put(body, processed);
                        exchange.getIn().setBody(processed);
                    }
                })
                .end()
                .log("Response: ${body}");


        // ----------------------
        // Resequencer
        // ----------------------
        from("direct:resequencer")
                .routeId("resequencerRoute")
                .log("Received for resequencing: ${body}")
                // Resequence by body (assuming body is numeric or lexically comparable)
                .resequence(simple("${body}"))
                .batch()
                .timeout(2000) // wait up to 2 seconds for more messages
                .log("After resequencing: ${body}");


        // ------------------------
        // Wire Tap
        // ------------------------
        from("direct:wiretap")
                .routeId("wiretapRoute")
                .log("Main flow received: ${body}")
                .wireTap("direct:auditFlow")
                .transform(simple("Main response: ${body}"));

        from("direct:auditFlow")
                .routeId("auditFlowRoute")
                .log("AUDIT copy of message: ${body}");



        // ------------------------
        // Load Balancer
        // ------------------------
        from("direct:loadbalancer")
                .routeId("loadBalancerRoute")
                .log("Incoming request: ${body}")
                .loadBalance().roundRobin()
                .to("direct:lbA", "direct:lbB", "direct:lbC");

        from("direct:lbA")
                .log("Processor A handling: ${body}")
                .transform(simple("Processed by A: ${body}"));

        from("direct:lbB")
                .log("Processor B handling: ${body}")
                .transform(simple("Processed by B: ${body}"));

        from("direct:lbC")
                .log("Processor C handling: ${body}")
                .transform(simple("Processed by C: ${body}"));




        // ------------------------
        // Throttler
        // ------------------------
        from("direct:throttler")
                .routeId("throttlerRoute")
                .log("Before throttling: ${body}")
                .throttle(2).timePeriodMillis(5000)   // allow 2 messages per 5 seconds
                .log("After throttling: ${body}")
                .transform(simple("Throttled response: ${body}"));



        // ------------------------


// Circuit Breaker
// ------------------------
        from("direct:circuitBreaker")
                .routeId("circuitBreakerRoute")
                .log("Received request for circuit breaker: ${body}")
                .circuitBreaker()
                .resilience4jConfiguration()
                .failureRateThreshold(50)        // % of failures before tripping
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(5)            // last 5 calls considered
                .waitDurationInOpenState(5000)   // stay open for 5 seconds
                .end()
                .to("direct:unstableService")       // primary call (may fail)
                .onFallback()
                .transform().simple("Fallback response for: ${body}")
                .end();

        from("direct:unstableService")
                .routeId("unstableServiceRoute")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    if (body.contains("fail")) {
                        throw new RuntimeException("Simulated failure for: " + body);
                    }
                    exchange.getIn().setBody("Success from service: " + body);
                });


    }




}
