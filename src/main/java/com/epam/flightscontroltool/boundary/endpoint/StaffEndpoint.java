package com.epam.flightscontroltool.boundary.endpoint;

import com.epam.flightscontroltool.boundary.dto.StaffMemberDto;
import com.epam.flightscontroltool.boundary.request.StaffMemberRequest;
import com.epam.flightscontroltool.control.service.StaffMemberService;
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
@RequestMapping("/staff/members")
public class StaffEndpoint {

    private final StaffMemberService staffMemberService;

    @ApiOperation(value = "Retrieving a page of staff members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Page of staff members"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<StaffMemberDto> getStaffMembersPage(
            @ApiParam("Flight id") @RequestParam(value = "flightId", required = false) Long flightId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving a page of staff members...");
        return this.staffMemberService.getStaffMembers(flightId, page, size);
    }

    @ApiOperation(value = "Creating a staff member")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A staff member was successfully created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StaffMemberDto create(@Valid @RequestBody StaffMemberRequest staffMemberRequest) {
        log.info("Creating a staff member...");
        return this.staffMemberService.create(staffMemberRequest);
    }

    @ApiOperation(value = "Updating a staff member")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A staff member was successfully updated"),
            @ApiResponse(code = 404, message = "A staff member was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StaffMemberDto update(
            @ApiParam("Staff member id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody StaffMemberRequest staffMemberRequest) {
        log.info("Updating a staff member with id {}...", id);
        return this.staffMemberService.update(id, staffMemberRequest);
    }

    @ApiOperation(value = "Deleting a staff member")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A staff member was successfully deleted"),
            @ApiResponse(code = 404, message = "A staff member was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Staff member id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a staff member with id {}", id);
        this.staffMemberService.delete(id);
    }
}
