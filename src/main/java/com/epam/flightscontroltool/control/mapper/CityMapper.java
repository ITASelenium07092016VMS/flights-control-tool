package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.CityDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedCityDto;
import com.epam.flightscontroltool.boundary.request.CityRequest;
import com.epam.flightscontroltool.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CityMapper {

    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "country.id", source = "countryId")
    public abstract City toCity(CityRequest cityRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "countryId", source = "country.id")
    public abstract CityDto toCityDto(City city);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "airports", source = "airports")
    public abstract ExtendedCityDto toExtendedCityDto(City city);
}
