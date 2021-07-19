package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.AirplaneDto;
import com.epam.flightscontroltool.boundary.request.AirplaneRequest;
import com.epam.flightscontroltool.control.service.AirplaneService;
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
@RequestMapping("/airplanes")
public class AirplaneEndpoint {

    private final AirplaneService airplaneService;

    @ApiOperation(value = "Retrieving a page of airplanes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A page of airplanes"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<AirplaneDto> getAirplanesPage(
            @ApiParam("Company id") @RequestParam(value = "companyId", required = false) Long companyId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of airplanes...");
        return this.airplaneService.getAirplanes(companyId, page, size);
    }

    @ApiOperation(value = "Creating an airplane")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An airplane was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AirplaneDto create(@Valid @RequestBody AirplaneRequest airplaneRequest) {
        log.info("Creating a new airplane...");
        return this.airplaneService.create(airplaneRequest);
    }

    @ApiOperation(value = "Updating an airplane")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airplane was successfully updated"),
            @ApiResponse(code = 404, message = "An airplane was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AirplaneDto update(
            @ApiParam("Airplane id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody AirplaneRequest airplaneRequest) {
        log.info("Updating an airplane with id {}...", id);
        return this.airplaneService.update(id, airplaneRequest);
    }

    @ApiOperation(value = "Deleting an airplane")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airplane was successfully deleted"),
            @ApiResponse(code = 404, message = "An airplane was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Airplane id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting an airplane with id {}", id);
        this.airplaneService.delete(id);
    }
}
