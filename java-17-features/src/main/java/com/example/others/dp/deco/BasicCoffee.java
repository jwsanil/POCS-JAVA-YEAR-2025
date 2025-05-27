package com.example.others.dp.deco;

import com.example.others.dp.deco.Coffe;

public class BasicCoffee implements Coffe {
    @Override
    public String getDescription() {
        return "basic coffe";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
