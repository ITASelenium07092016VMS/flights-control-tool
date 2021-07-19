package com.epam.flightscontroltool.boundary.exception;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long id) {
        super("Country with id " + id + " does not exist");
    }
}