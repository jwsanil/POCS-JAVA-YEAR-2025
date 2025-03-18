package com.example.others.dp.mp.one;

public class ChatUser extends User{


    public ChatUser(ChatMediator chatMediator, String name) {
        super(chatMediator, name);
    }

    @Override
    void sendMessage(String message) {
        System.out.println(name+" sends: " + message);
        chatMediator.sendMessage(message,this);
    }

    @Override
    void receiveMessage(String message, User sender) {
        System.out.println(name + " receives from " + sender.name + ": " + message);
    }
}
