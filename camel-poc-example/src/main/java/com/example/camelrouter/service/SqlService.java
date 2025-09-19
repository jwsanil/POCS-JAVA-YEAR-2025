package com.example.camelrouter.service;

import com.example.camelrouter.dao.MessageRepository;
import com.example.camelrouter.entity.MessageEntity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("sqlService") // Camel bean name
public class SqlService {

    private final MessageRepository repository;

    public SqlService(MessageRepository repository) {
        this.repository = repository;
    }

    // Insert message
    public void insertMessage(String message) {
        repository.save(new MessageEntity(message));
    }

    // Fetch all messages
    public List<MessageEntity> getAllMessages() {
        return repository.findAll();
    }
}
