package com.example.others.dp.mp.two;

public class PassengerPlane extends Aircraft {

    public PassengerPlane(AirTrafiicControl atc,String name){
        super(atc,name);
    }

    @Override
    public void requestLanding() {

        System.out.println(name+": Requesting permission to land.");
        atc.SendLandingRequest(this);

    }
}
