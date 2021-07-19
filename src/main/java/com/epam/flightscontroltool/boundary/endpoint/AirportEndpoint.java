package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.AirportDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedAirportDto;
import com.epam.flightscontroltool.boundary.request.AirportRequest;
import com.epam.flightscontroltool.control.service.AirportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/airports")
public class AirportEndpoint {

    private final AirportService airportService;

    @ApiOperation(value = "Retrieving a page of airports")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A page of airports"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<AirportDto> getAirportsPage(
            @ApiParam("City id") @RequestParam(value = "cityId", required = false) Long cityId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of airports...");
        return this.airportService.getAirports(cityId, page, size);
    }

    @ApiOperation(value = "Retrieving an airport details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Airport details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ExtendedAirportDto getAirport(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of an airport with id {}", id);
        return this.airportService.getAirport(id);
    }

    @ApiOperation(value = "Creating an airport")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An airport was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AirportDto create(@Valid @RequestBody AirportRequest airportRequest) {
        log.info("Creating a new airport...");
        return this.airportService.create(airportRequest);
    }

    @ApiOperation(value = "Updating an airport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airport was successfully updated"),
            @ApiResponse(code = 404, message = "An airport was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AirportDto update(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody AirportRequest airportRequest) {
        log.info("Updating an airport with id {}...", id);
        return this.airportService.update(id, airportRequest);
    }

    @ApiOperation(value = "Deleting an airport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airport was successfully deleted"),
            @ApiResponse(code = 404, message = "An airport was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting an airport with id {}", id);
        this.airportService.delete(id);
    }
}
