package com.example.others.dp.mp.one;

public interface ChatMediator {


    abstract void sendMessage( String message, User user);

    abstract void addUser(User user) ;
}
