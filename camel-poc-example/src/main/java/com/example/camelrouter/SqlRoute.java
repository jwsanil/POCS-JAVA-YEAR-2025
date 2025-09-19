package com.example.camelrouter;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqlRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Route to insert message via SqlService
        from("direct:sqlInsert")
                .routeId("sqlInsertRoute")
                .log("Inserting into SQL via service: ${body}")
                .bean("sqlService", "insertMessage");

        // Route to fetch messages via SqlService
        from("direct:sqlSelect")
                .routeId("sqlSelectRoute")
                .log("Fetching all messages via service")
                .bean("sqlService", "getAllMessages")
                .log("SQLite query result: ${body}");
    }
}
