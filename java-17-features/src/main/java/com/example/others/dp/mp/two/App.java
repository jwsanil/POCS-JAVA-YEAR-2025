package com.example.others.dp.mp.two;

public class App {

    public static void main(String[] args) {

        ATCTower atcTower = new ATCTower();

        Aircraft plane1= new PassengerPlane(atcTower,"indigo 101");
        Aircraft plane2 = new CargoPlane(atcTower, "cargo 2202");

        plane1.requestLanding();
        plane2.requestLanding();
        atcTower.runwayCleard();
        plane2.requestLanding();

    }
}
