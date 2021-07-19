package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.CityMapper;
import com.epam.flightscontroltool.entity.Airport;
import com.epam.flightscontroltool.entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ExtendedCityDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private CountryDto country;

    @JsonProperty("airports")
    private List<AirportDto> airports;

    public static ExtendedCityDto from(City city) {
        return CityMapper.INSTANCE.toExtendedCityDto(city);
    }
}
