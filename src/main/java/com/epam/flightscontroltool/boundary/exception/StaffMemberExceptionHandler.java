package com.epam.flightscontroltool.boundary.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class StaffMemberExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StaffMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorMessage handleException(RuntimeException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage());
    }
}
