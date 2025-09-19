package com.example.camelrouter.controller;



import com.example.camelrouter.entity.MessageEntity;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    @Autowired
    private ProducerTemplate producerTemplate;

    // Insert a message into SQLite
    @PostMapping("/insert")
    public String insertMessage(@RequestBody String message) {
        producerTemplate.sendBody("direct:sqlInsert", message);
        return "Inserted into SQLite: " + message;
    }

    @GetMapping("/all")
    public List<MessageEntity> getAllMessages() {
        List<?> list = producerTemplate.requestBody("direct:sqlSelect", null, List.class);
        return list.stream()
                .map(obj -> (MessageEntity) obj)
                .toList();
    }

}
