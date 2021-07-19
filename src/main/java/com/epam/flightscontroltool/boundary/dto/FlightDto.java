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
public class FlightDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("airplaneId")
    private Long airplaneId;

    @JsonProperty("departureAirportId")
    private Long departureAirportId;

    @JsonProperty("arrivalAirportId")
    private Long arrivalAirportId;

    @JsonProperty("departureDateTime")
    private LocalDateTime departureDateTime;

    @JsonProperty("arrivalDateTime")
    private LocalDateTime arrivalDateTime;

    @JsonProperty("basePrice")
    private BigDecimal basePrice;

    public static FlightDto from(Flight flight) {
        return FlightMapper.INSTANCE.toFlightDto(flight);
    }
}
