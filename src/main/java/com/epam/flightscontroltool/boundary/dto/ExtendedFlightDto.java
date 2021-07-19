package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.FlightMapper;
import com.epam.flightscontroltool.entity.Flight;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExtendedFlightDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("airplane")
    private AirplaneDto airplane;

    @JsonProperty("departureAirport")
    private AirportDto departureAirport;

    @JsonProperty("arrivalAirport")
    private AirportDto arrivalAirport;

    @JsonProperty("departureDateTime")
    private LocalDateTime departureDateTime;

    @JsonProperty("arrivalDateTime")
    private LocalDateTime arrivalDateTime;

    @JsonProperty("basePrice")
    private BigDecimal basePrice;

    public static ExtendedFlightDto from(Flight flight) {
        return FlightMapper.INSTANCE.toExtendedFlightDto(flight);
    }
}
