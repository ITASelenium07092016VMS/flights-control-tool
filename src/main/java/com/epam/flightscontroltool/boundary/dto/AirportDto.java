package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.AirportMapper;
import com.epam.flightscontroltool.entity.Airport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirportDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cityId")
    private Long cityId;

    public static AirportDto from(Airport airport) {
        return AirportMapper.INSTANCE.toAirportDto(airport);
    }
}
