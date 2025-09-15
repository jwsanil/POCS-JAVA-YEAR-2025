package com.example.camelrouter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageTranslatorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:translator").routeId("messageTranslatorRoute").log("Original message: ${body}")

                // Use Camel’s Message Translator pattern:
                // Convert String -> Map -> JSON
                .process(exchange -> {
                    String input = exchange.getIn().getBody(String.class);
                    Map<String, Object> response = new HashMap<>();
                    response.put("translatedMessage", input.toUpperCase());
                    exchange.getIn().setBody(response);
                })

                // Marshal (Map -> JSON String) using Jackson
                .marshal().json().log("Translated JSON message: ${body}");


        // ------------------------
        // Content Enricher Route
        // ------------------------
        from("direct:enricher").routeId("contentEnricherRoute").log("Original message: ${body}")

                // Enrich message with data from a "lookup" endpoint
                .enrich("direct:lookupService", (original, resource) -> {
                    String body = original.getIn().getBody(String.class);

                    Map<String, Object> enriched = new HashMap<>();
                    enriched.put("originalMessage", body);

                    Map<String, Object> extra = resource.getIn().getBody(Map.class);
                    enriched.putAll(extra);

                    original.getIn().setBody(enriched);
                    return original;
                })

                // Marshal the final enriched message to JSON
                .marshal().json().log("Enriched message: ${body}");


        // ------------------------
        // Mock lookup service for Enricher
        // ------------------------
        from("direct:lookupService").process(exchange -> {
            Map<String, Object> extra = new HashMap<>();
            extra.put("extraInfo", "Fetched from lookup service");
            extra.put("timestamp", System.currentTimeMillis());
            exchange.getIn().setBody(extra);
        });



        // ------------------------
        // Content Filter Route
        // ------------------------
        from("direct:filter")
                .routeId("contentFilterRoute")
                .log("Filtering message: ${body}")
                .filter(simple("${body} contains 'important'"))
                .log("Message passed filter: ${body}")
                .end()
                .log("Finished Content Filter route");




        // ------------------------
        // Claim Check Route
        // ------------------------
        from("direct:claimCheck")
                .routeId("claimCheckRoute")
                .log("Original message: ${body}")

                // Store part of the message temporarily in a property
                .setProperty("storedPart", simple("${body}"))

                // Simulate some processing that changes the body
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("Processed body without original part");
                })
                .log("Body after processing: ${body}")

                // Restore the stored part from property
                .process(exchange -> {
                    String original = exchange.getProperty("storedPart", String.class);
                    exchange.getIn().setBody("Restored original: " + original + " | " + exchange.getIn().getBody(String.class));
                })
                .log("Final message after Claim Check: ${body}");



    }




}
