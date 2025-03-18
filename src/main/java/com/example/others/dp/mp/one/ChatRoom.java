package com.example.others.dp.mp.one;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator{


    private final List<User> users =  new ArrayList<>();

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {

            if (user != sender) {

                user.receiveMessage(message, sender);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
