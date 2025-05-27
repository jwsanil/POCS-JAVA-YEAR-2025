package com.example.others.dp.deco.pt;

import com.example.others.dp.deco.Coffe;

public  class SugarDecorator extends CoffeDecorator {
    public SugarDecorator(Coffe coffe) {
        super(coffe);
    }

    @Override
    public String getDescription() {
        return coffe.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffe.getCost() + 0.3;
    }
}
