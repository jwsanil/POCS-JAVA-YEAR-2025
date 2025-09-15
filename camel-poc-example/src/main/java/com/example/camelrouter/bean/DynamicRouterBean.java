package com.example.camelrouter.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component
public class DynamicRouterBean {

    /**
     * Returns the next endpoint to route to or null to stop
     */
    public String route(String body, Exchange exchange) {
        Integer iteration = exchange.getProperty("myIteration", Integer.class);
        if (iteration == null) {
            iteration = 0;
        }

        System.out.println("Iteration: " + iteration + ", body: " + body);

        switch (iteration) {
            case 0:
                exchange.setProperty("myIteration", iteration + 1);
                return "direct:dynA";
            case 1:
                exchange.setProperty("myIteration", iteration + 1);
                return "direct:dynB";
            default:
                exchange.removeProperty("myIteration"); // cleanup
                return null;
        }
    }
}