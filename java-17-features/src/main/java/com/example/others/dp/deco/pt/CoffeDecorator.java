package com.example.others.dp.deco.pt;

import com.example.others.dp.deco.Coffe;

 abstract class CoffeDecorator implements Coffe {

     protected Coffe coffe;

     public CoffeDecorator(Coffe coffe) {

         this.coffe = coffe;
     }


 }
