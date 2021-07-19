package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.ExtendedFlightDto;
import com.epam.flightscontroltool.boundary.dto.FlightDto;
import com.epam.flightscontroltool.boundary.request.FlightRequest;
import com.epam.flightscontroltool.control.service.FlightService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightEndpoint {

    private final FlightService flightService;

    @ApiOperation(value = "Retrieving a page of flights")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Page of flights"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<FlightDto> getFlightsPage(
            @ApiParam("Departure city id") @RequestParam(value = "departureCityId", required = false) Long departureCityId,
            @ApiParam("Arrival city id") @RequestParam(value = "arrivalCityId", required = false) Long arrivalCityId,
            @ApiParam("Departure time") @RequestParam(value = "departureDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @ApiParam("Arrival time") @RequestParam(value = "arrivalDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of flights...");
        return this.flightService.getFlights(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, page, size);
    }

    @ApiOperation(value = "Retrieving a flight details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Flight details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ExtendedFlightDto getFlightById(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of a flight with id {}", id);
        return this.flightService.getFlight(id);
    }

    @ApiOperation(value = "Creating a flight")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A flight was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public FlightDto create(@Valid @RequestBody FlightRequest flightRequest) {
        log.info("Creating a new flight...");
        return this.flightService.create(flightRequest);
    }

    @ApiOperation(value = "Updating a flight")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A flight was successfully updated"),
            @ApiResponse(code = 404, message = "A flight was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public FlightDto update(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody FlightRequest flightRequest) {
        log.info("Updating a flight with id {}...", id);
        return this.flightService.update(id, flightRequest);
    }

    @ApiOperation(value = "Deleting a flight")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A flight was successfully deleted"),
            @ApiResponse(code = 404, message = "A flight was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a flight with id {}", id);
        this.flightService.delete(id);
    }
}
