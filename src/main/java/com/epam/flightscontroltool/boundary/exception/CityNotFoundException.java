package com.epam.flightscontroltool.boundary.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long id) {
        super("City with id " + id + " does not exist");
    }
}