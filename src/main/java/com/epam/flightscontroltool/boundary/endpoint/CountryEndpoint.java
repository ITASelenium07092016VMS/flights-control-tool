package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.CountryDto;
import com.epam.flightscontroltool.boundary.request.CountryRequest;
import com.epam.flightscontroltool.control.service.CountryService;
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
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryEndpoint {

    private final CountryService countryService;

    @ApiOperation(value = "Retrieving a page of countries")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Page of countries"),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<CountryDto> getCountriesPage(
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of countries...");
        return this.countryService.getCountries(page, size);
    }

    @ApiOperation(value = "Creating a country")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A country was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CountryDto create(@Valid @RequestBody CountryRequest countryRequest) {
        log.info("Creating a new country...");
        return this.countryService.create(countryRequest);
    }

    @ApiOperation(value = "Updating a country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A country was successfully updated"),
            @ApiResponse(code = 404, message = "A country was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CountryDto update(
            @ApiParam("Country id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CountryRequest countryRequest) {
        log.info("Updating a country with id {}...", id);
        return this.countryService.update(id, countryRequest);
    }

    @ApiOperation(value = "Deleting a country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A country was successfully deleted"),
            @ApiResponse(code = 404, message = "A country was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Country id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a country with id {}", id);
        this.countryService.delete(id);
    }
}
