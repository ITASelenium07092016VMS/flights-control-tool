package com.epam.flightscontroltool.boundary.exception;

public class AirplaneNotFoundException extends RuntimeException {

    public AirplaneNotFoundException(Long id) {
        super("Airplane with id " + id + " does not exist");
    }
}