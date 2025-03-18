package com.example.others.dp.mp.one;

public class MediatorPatterApp {


    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");

        User user2  = new ChatUser( chatRoom,"Bob");

        User user3 = new ChatUser(chatRoom, "charlie");
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        user1.sendMessage("hello everyone");

        user2.sendMessage("Hi Alice");

    }



}
