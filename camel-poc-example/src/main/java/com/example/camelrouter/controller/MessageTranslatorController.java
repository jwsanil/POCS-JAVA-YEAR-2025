package com.example.camelrouter.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translator")
public class MessageTranslatorController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping
    public String translateMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:translator", message, String.class);
    }

    // --- Content Enricher Endpoint ---
    @PostMapping("/enricher")
    public String enrichMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:enricher", message, String.class);
    }

    // --- Content Filter Endpoint ---
    @PostMapping("/filter")
    public String filterMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:filter", message, String.class);
    }

    // --- Claim Check Endpoint ---
    @PostMapping("/claimCheck")
    public String claimCheckMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:claimCheck", message, String.class);
    }


}
