package com.example.camelrouter.controller;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endpoint")
public class EndpointController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    // Optional: trigger the scheduled route manually
    @GetMapping("/trigger")
    public String triggerScheduledRoute() {
        producerTemplate.sendBody("direct:scheduledTask", "Manual trigger");
        return "Endpoint route triggered manually!";
    }


    // New manual trigger for SEDA queue
    @GetMapping("/seda")
    public String triggerSedaRoute() {
        producerTemplate.sendBody("seda:asyncQueue", "Manual SEDA trigger");
        return "Message submitted to SEDA queue!";
    }

    // Optional: manual trigger to process file (normally automatic)
    @GetMapping("/file")
    public String triggerFileRoute() {
        producerTemplate.sendBody("file:input?noop=true", "Manual file trigger message");
        return "Message sent to file input folder!";
    }

    @GetMapping("/ftp")
    public String triggerFtpRoute() {
        producerTemplate.sendBody("direct:ftpRoute", "Manual FTP trigger");
        return "FTP route triggered manually!";
    }

    // Manual trigger for JMS
    @PostMapping("/jms")
    public String triggerJmsRoute(@RequestBody String message) {
        producerTemplate.sendBody("direct:jmsRoute", message);
        return "Message sent to JMS queue!";
    }
    // New endpoint to read from JMS queue
    @GetMapping("/jms/read")
    public String readJmsMessage() {
        // Receive message from JMS queue, wait max 1 second
        Exchange exchange = consumerTemplate.receive("jms:queue:exampleQueue", 1000);
        if (exchange != null) {
            String body = exchange.getIn().getBody(String.class);
            return "Read from JMS queue: " + body;
        } else {
            return "No messages in JMS queue.";
        }
    }

    @GetMapping("/kafka")
    public String triggerKafkaRoute() {
        producerTemplate.sendBody("direct:kafkaRoute", "Manual Kafka trigger message");
        return "Message sent to Kafka topic!";
    }

    @GetMapping("/kafka/read")
    public String readKafkaMessage() {
        Exchange exchange = consumerTemplate.receive("kafka:exampleTopic?brokers=localhost:9092", 1000);
        if (exchange != null) {
            return "Received from Kafka topic: " + exchange.getIn().getBody(String.class);
        } else {
            return "No messages in Kafka topic.";
        }
    }


    @GetMapping("/jms/dlc")
    public String readDlcMessage() {
        Exchange exchange = consumerTemplate.receive("jms:queue:deadLetterQueue", 1000);
        if (exchange != null) {
            return "Received from Dead Letter Queue: " + exchange.getIn().getBody(String.class);
        } else {
            return "No messages in Dead Letter Queue.";
        }
    }

}