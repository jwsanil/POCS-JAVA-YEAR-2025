package com.example.others.dp.vp;

public interface AircraftVisitor {

    void visit(PassengerPlane passengerPlane);

    void visit(CargoPlane cargoPlane);


}
