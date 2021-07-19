package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.CityMapper;
import com.epam.flightscontroltool.entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("countryId")
    private Long countryId;

    public static CityDto from(City city) {
        return CityMapper.INSTANCE.toCityDto(city);
    }
}
