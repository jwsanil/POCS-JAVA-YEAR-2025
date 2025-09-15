package com.example.camelrouter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.camel.ProducerTemplate;
@RestController
@RequestMapping("/api/management")  // renamed controller
public class ManagementController {   // renamed controller

    @Autowired
    private ProducerTemplate producerTemplate;

    // Idempotent Consumer Endpoint
    @PostMapping("/idempotent")
    public String sendMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:idempotent", message, String.class);
    }

    // Resequencer
    @PostMapping("/resequencer")
    public String resequencerMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:resequencer", message, String.class);
    }


    @PostMapping("/wiretap")
    public String wiretapMessage(@RequestBody String message) {
        return producerTemplate.requestBody("direct:wiretap", message, String.class);
    }


    @PostMapping("/loadbalancer")
    public String loadBalancer(@RequestBody String message) {
        return producerTemplate.requestBody("direct:loadbalancer", message, String.class);
    }

    @PostMapping("/throttler")
    public String throttler(@RequestBody String message) {
        return producerTemplate.requestBody("direct:throttler", message, String.class);
    }

    @PostMapping("/circuitBreaker")
    public String callCircuitBreaker(@RequestBody String message) {
        return producerTemplate.requestBody("direct:circuitBreaker", message, String.class);
    }

}