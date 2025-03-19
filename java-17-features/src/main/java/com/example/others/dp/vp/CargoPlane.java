package com.example.others.dp.vp;

public class CargoPlane extends Aircraft {


    public CargoPlane(AirTrafiicControl atc, String name) {
        super(atc, name);
    }

    @Override
    public void requestLanding() {
        System.out.println(name + ": Requesting permission to land.");
        atc.SendLandingRequest(this);
    }

    @Override
    public void accept(AircraftVisitor visitor) {
        visitor.visit(this);
    }


}
