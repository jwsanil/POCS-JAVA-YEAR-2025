package com.example.others.dp.mp.two;
//mediator interface

 interface AirTrafiicControl {

    void registerAircraft(Aircraft airCraft);

    abstract void SendLandingRequest(Aircraft airCraft);
}
