package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.AirplaneMapper;
import com.epam.flightscontroltool.entity.Airplane;
import com.epam.flightscontroltool.entity.Manufacturer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private String number;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("companyId")
    private Long companyId;

    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;

    public static AirplaneDto from(Airplane airplane) {
        return AirplaneMapper.INSTANCE.toAirplaneDto(airplane);
    }
}
