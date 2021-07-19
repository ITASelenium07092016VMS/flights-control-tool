package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.CountryMapper;
import com.epam.flightscontroltool.entity.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public static CountryDto from(Country country) {
        return CountryMapper.INSTANCE.toCountryDto(country);
    }
}
