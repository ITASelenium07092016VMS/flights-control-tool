package com.epam.flightscontroltool.entity;

public enum Position {

    PILOT("Pilot"),
    FLIGHT_ATTENDANT("Flight attendant"),
    ENGINEER("Engineer"),
    MECHANIC("Mechanic");

    private String position;

    Position(String position) {
        this.position = position;
    }
}
