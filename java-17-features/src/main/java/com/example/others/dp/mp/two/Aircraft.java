package com.example.others.dp.mp.two;

abstract class Aircraft {

    protected  AirTrafiicControl atc;
    protected String name;

    public Aircraft(AirTrafiicControl atc, String name){

        this.atc =atc;
        this.name = name;
        atc.registerAircraft(this);
    }

    public String getName() {
        return name;
    }

    public abstract void requestLanding();
}
