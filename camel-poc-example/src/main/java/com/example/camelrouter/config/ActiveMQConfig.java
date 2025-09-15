package com.example.camelrouter.config;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig {

    @Bean
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616"); // JMS connector
       /* broker.addConnector("http://0.0.0.0:8161");  // Web console*/
        broker.setPersistent(false);
        broker.setUseJmx(true);
        broker.setBrokerName("embeddedBroker");
        broker.setUseShutdownHook(true);
        return broker;
    }
}
