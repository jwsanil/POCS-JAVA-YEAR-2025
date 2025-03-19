package com.example.others.dp.vp;

public class MaintainanceVisitor implements  AircraftVisitor{
    @Override
    public void visit(PassengerPlane passengerPlane) {
        System.out.println("performing maintainance"+passengerPlane.getName());
    }

    @Override
    public void visit(CargoPlane cargoPlane) {
        System.out.println("Performing maintaince"+ cargoPlane.getName());
    }
}
