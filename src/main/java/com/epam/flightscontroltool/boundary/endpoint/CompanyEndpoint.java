package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.CompanyDto;
import com.epam.flightscontroltool.boundary.request.CompanyRequest;
import com.epam.flightscontroltool.control.service.CompanyService;
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
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyEndpoint {

    private final CompanyService companyService;

    @ApiOperation(value = "Retrieving a page of airplane companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Page of airplane companies"),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<CompanyDto> getCompaniesPage(
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of airplane companies...");
        return this.companyService.getCompanies(page, size);
    }

    @ApiOperation(value = "Creating an airplane company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An airplane company was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CompanyDto create(@Valid @RequestBody CompanyRequest companyRequest) {
        log.info("Creating a new airplane company...");
        return this.companyService.create(companyRequest);
    }

    @ApiOperation(value = "Updating an airplane company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airplane company was successfully updated"),
            @ApiResponse(code = 404, message = "An airplane company was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CompanyDto update(
            @ApiParam("Airplane company id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CompanyRequest companyRequest) {
        log.info("Updating an airplane company with id {}...", id);
        return this.companyService.update(id, companyRequest);
    }

    @ApiOperation(value = "Deleting an airplane company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airplane company was successfully deleted"),
            @ApiResponse(code = 404, message = "An airplane company was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Airplane company id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a country with id {}", id);
        this.companyService.delete(id);
    }
}
