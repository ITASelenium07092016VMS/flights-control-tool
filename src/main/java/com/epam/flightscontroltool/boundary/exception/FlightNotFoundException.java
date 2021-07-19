package com.epam.flightscontroltool.boundary.exception;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(Long id) {
        super("Flight with id " + id + " does not exist");
    }
}
