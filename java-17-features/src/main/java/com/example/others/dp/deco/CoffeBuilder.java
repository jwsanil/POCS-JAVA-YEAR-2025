package com.example.others.dp.deco;

import com.example.others.dp.deco.pt.MilkDecorator;
import com.example.others.dp.deco.pt.SugarDecorator;

public class CoffeBuilder {

    public static Coffe withMilk(Coffe coffe){

        return  new MilkDecorator(coffe);
    }
    public static Coffe withSugar(Coffe coffe){

        return  new SugarDecorator(coffe);
    }
}
