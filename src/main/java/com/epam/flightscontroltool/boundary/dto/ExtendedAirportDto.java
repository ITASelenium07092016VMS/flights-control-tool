package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.AirportMapper;
import com.epam.flightscontroltool.entity.Airport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExtendedAirportDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private CityDto city;

    public static ExtendedAirportDto from(Airport airport) {
        return AirportMapper.INSTANCE.toExtendedAirportDto(airport);
    }
}
