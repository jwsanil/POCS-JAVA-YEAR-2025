package com.example.others.dp.vp;
//mediator interface

interface AirTrafiicControl {

    void registerAircraft(Aircraft airCraft);

    abstract void SendLandingRequest(Aircraft airCraft);
}
