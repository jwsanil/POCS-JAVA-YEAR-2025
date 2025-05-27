package com.example.others.dp.deco;

public class Main {
    public static void main(String[] args) {

     Coffe coffe = CoffeBuilder.withMilk(CoffeBuilder.withSugar( new BasicCoffee()));


        System.out.println(coffe.getDescription());
        System.out.println("cost: $"+ coffe.getCost());
    }
}
