package com.epam.flightscontroltool.boundary.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {

    //TODO: the class should be a wrapper for flight search
    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

}
