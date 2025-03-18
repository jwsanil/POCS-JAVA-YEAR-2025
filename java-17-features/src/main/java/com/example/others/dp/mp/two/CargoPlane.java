package com.example.others.dp.mp.two;

public class CargoPlane extends Aircraft {


    public CargoPlane(AirTrafiicControl atc, String name) {
        super(atc, name);
    }

    @Override
    public void requestLanding() {
        System.out.println(name + ": Requesting permission to land.");
        atc.SendLandingRequest(this);
    }
}
