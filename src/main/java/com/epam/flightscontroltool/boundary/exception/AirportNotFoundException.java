package com.epam.flightscontroltool.boundary.exception;

public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(Long id) {
        super("Airport with id " + id + " does not exist");
    }
}