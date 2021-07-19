package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.ExtendedFlightDto;
import com.epam.flightscontroltool.boundary.dto.FlightDto;
import com.epam.flightscontroltool.boundary.request.FlightRequest;
import com.epam.flightscontroltool.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FlightMapper {

    public static final FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "airplaneId", source = "airplane.id")
    @Mapping(target = "departureAirportId", source = "departureAirport.id")
    @Mapping(target = "arrivalAirportId", source = "arrivalAirport.id")
    @Mapping(target = "departureDateTime", source = "departureDateTime")
    @Mapping(target = "arrivalDateTime", source = "arrivalDateTime")
    @Mapping(target = "basePrice", source = "basePrice")
    public abstract FlightDto toFlightDto(Flight flight);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "airplane", source = "airplane")
    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "departureDateTime", source = "departureDateTime")
    @Mapping(target = "arrivalDateTime", source = "arrivalDateTime")
    @Mapping(target = "basePrice", source = "basePrice")
    public abstract ExtendedFlightDto toExtendedFlightDto(Flight flight);

    @Mapping(target = "airplane.id", source = "airplaneId")
    @Mapping(target = "departureAirport.id", source = "departureAirportId")
    @Mapping(target = "arrivalAirport.id", source = "arrivalAirportId")
    @Mapping(target = "departureDateTime", source = "departureDateTime")
    @Mapping(target = "arrivalDateTime", source = "arrivalDateTime")
    @Mapping(target = "basePrice", source = "basePrice")
    public abstract Flight toFlight(FlightRequest flightRequest);
}
