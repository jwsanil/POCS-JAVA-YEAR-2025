package com.example.camelrouter.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping
    public String sendMessage(@RequestBody String message) {
        producerTemplate.sendBody("direct:start", message);
        return "Message sent: " + message;
    }

    @PostMapping("/recipient")
    public String sendRecipientListMessage(@RequestBody String message) {
        producerTemplate.sendBody("direct:recipientListExample", message);
        return "Message sent to recipient list endpoints: " + message;
    }


    @PostMapping("/split")
    public String sendSplitMessage(@RequestBody String message) {
        // Returns the processed message of the last split piece
        String result = producerTemplate.requestBody("direct:splitExample", message, String.class);
        return result;
    }

    @PostMapping("/multicast")
    public String sendMulticastMessage(@RequestBody String message) {
        // Returns the aggregated result from all multicast endpoints
        String result = producerTemplate.requestBody("direct:multicastExample", message, String.class);
        return result;
    }


    @PostMapping("/routingSlip")
    public String sendRoutingSlipMessage(@RequestBody String message) {
        // Returns the message after all routing slip steps
        String result = producerTemplate.requestBody("direct:routingSlipExample", message, String.class);
        return result;
    }



    @PostMapping("/dynamicRouter")
    public String sendDynamicRouterMessage(@RequestBody String message) {
        String result = producerTemplate.requestBody("direct:dynamicRouterExample", message, String.class);
        return result;
    }




}
