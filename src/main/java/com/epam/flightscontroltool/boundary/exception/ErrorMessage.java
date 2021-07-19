package com.epam.flightscontroltool.boundary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private final int statusCode;

    private final LocalDateTime dateTime;

    private final String message;
}
