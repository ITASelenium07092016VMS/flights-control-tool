package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.AirplaneDto;
import com.epam.flightscontroltool.boundary.request.AirplaneRequest;
import com.epam.flightscontroltool.entity.Airplane;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AirplaneMapper {

    public static final AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "capacity", source = "capacity")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "manufacturer", source = "manufacturer")
    public abstract AirplaneDto toAirplaneDto(Airplane airplane);

    @Mapping(target = "number", source = "number")
    @Mapping(target = "capacity", source = "capacity")
    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "manufacturer", source = "manufacturer")
    public abstract Airplane toAirplane(AirplaneRequest airplaneRequest);
}
