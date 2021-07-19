package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.AirportDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedAirportDto;
import com.epam.flightscontroltool.boundary.request.AirportRequest;
import com.epam.flightscontroltool.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AirportMapper {

    public static final AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "cityId", source = "city.id")
    public abstract AirportDto toAirportDto(Airport airport);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "city.id", source = "cityId")
    public abstract Airport toAirport(AirportRequest airportRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "city", source = "city")
    public abstract ExtendedAirportDto toExtendedAirportDto(Airport airport);
}
