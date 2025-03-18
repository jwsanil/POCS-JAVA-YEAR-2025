package com.example.others.dp.mp.two;

import java.util.ArrayList;
import java.util.List;

public class ATCTower implements AirTrafiicControl {

    private final List<Aircraft> aircrafts = new ArrayList<>();

    private boolean runwayAvailable = true;


    @Override
    public void registerAircraft(Aircraft airCraft) {

        aircrafts.add(airCraft);
    }

    @Override
    public void SendLandingRequest(Aircraft airCraft) {

        if (runwayAvailable) {

            System.out.println("ATC : " + airCraft.getName() + " is cleared to land.");
            runwayAvailable = false;
        } else {

            System.out.println("ATC : " + airCraft.getName() + "must wait. Runway is busy.");
        }


    }

    public void runwayCleard() {
        System.out.println("ATC: Runway is now available.");
        runwayAvailable = true;

    }
}
