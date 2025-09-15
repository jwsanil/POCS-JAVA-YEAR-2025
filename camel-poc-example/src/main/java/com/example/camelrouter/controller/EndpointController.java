package com.example.camelrouter.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endpoint")
public class EndpointController {

    @Autowired
    private ProducerTemplate producerTemplate;

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

}