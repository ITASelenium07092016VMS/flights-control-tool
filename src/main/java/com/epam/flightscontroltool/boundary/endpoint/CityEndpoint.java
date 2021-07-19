package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.CityDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedCityDto;
import com.epam.flightscontroltool.boundary.request.CityRequest;
import com.epam.flightscontroltool.control.service.CityService;
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
@RequestMapping("/cities")
public class CityEndpoint {

    private final CityService cityService;

    @ApiOperation(value = "Retrieving a page of cities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A page of cities"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<CityDto> getCitiesPage(
            @ApiParam("Country id") @RequestParam(value = "countryId", required = false) Long countryId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of cities...");
        return this.cityService.getCities(countryId, page, size);
    }

    @ApiOperation(value = "Retrieving a city details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ExtendedCityDto getCity(
            @ApiParam("City id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of a city with id {}", id);
        return this.cityService.getCity(id);
    }

    @ApiOperation(value = "Creating a city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A city was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CityDto create(@Valid @RequestBody CityRequest cityRequest) {
        log.info("Creating a new city...");
        return this.cityService.create(cityRequest);
    }

    @ApiOperation(value = "Updating a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A city was successfully updated"),
            @ApiResponse(code = 404, message = "A city was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CityDto update(
            @ApiParam("City id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CityRequest cityRequest) {
        log.info("Updating a city with id {}...", id);
        return this.cityService.update(id, cityRequest);
    }

    @ApiOperation(value = "Deleting a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A city was successfully deleted"),
            @ApiResponse(code = 404, message = "A city was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("City id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a city with id {}", id);
        this.cityService.delete(id);
    }
}
