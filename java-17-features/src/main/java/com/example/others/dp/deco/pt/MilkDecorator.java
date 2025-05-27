package com.example.others.dp.deco.pt;

import com.example.others.dp.deco.Coffe;

public  class MilkDecorator extends CoffeDecorator {
    public MilkDecorator(Coffe coffe) {
        super(coffe);
    }

    @Override
    public String getDescription() {
        return coffe.getDescription()+", Milk";
    }

    @Override
    public double getCost() {
        return coffe.getCost() + 0.5;
    }
}
