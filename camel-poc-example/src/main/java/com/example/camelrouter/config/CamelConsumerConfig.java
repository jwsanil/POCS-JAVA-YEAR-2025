package com.example.camelrouter.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConsumerConfig {

    @Bean
    public ConsumerTemplate consumerTemplate(CamelContext camelContext) {
        return camelContext.createConsumerTemplate();
    }
}

