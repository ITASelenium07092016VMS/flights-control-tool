package com.epam.flightscontroltool.boundary.exception;

public class StaffMemberNotFoundException extends RuntimeException {

    public StaffMemberNotFoundException(Long id) {
        super("Staff member with id " + id + " does not exist");
    }
}