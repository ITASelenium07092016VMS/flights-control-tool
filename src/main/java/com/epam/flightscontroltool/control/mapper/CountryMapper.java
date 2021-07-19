package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.CountryDto;
import com.epam.flightscontroltool.boundary.request.CountryRequest;
import com.epam.flightscontroltool.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CountryMapper {

    public static final CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract CountryDto toCountryDto(Country country);

    @Mapping(target = "name", source = "name")
    public abstract Country toCountry(CountryRequest countryRequest);
}
