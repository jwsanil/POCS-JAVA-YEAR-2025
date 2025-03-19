package com.example.others.dp.vp;


public class App {

    public static void main(String[] args) {

        ATCTower atcTower = new ATCTower();//mediator
        AircraftVisitor aircraftVisitor = new MaintainanceVisitor();

        Aircraft plane1 = new PassengerPlane(atcTower, "indigo 101");
        Aircraft plane2 = new CargoPlane(atcTower, "cargo 2202");

        plane1.requestLanding();
        plane2.requestLanding();
        atcTower.runwayCleard();
        plane2.requestLanding();

        plane1.accept(aircraftVisitor);
        plane2.accept(aircraftVisitor);

    }
}
