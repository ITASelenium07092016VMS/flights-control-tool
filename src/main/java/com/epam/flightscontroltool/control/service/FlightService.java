package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.ExtendedFlightDto;
import com.epam.flightscontroltool.boundary.dto.FlightDto;
import com.epam.flightscontroltool.boundary.exception.AirplaneNotFoundException;
import com.epam.flightscontroltool.boundary.exception.AirportNotFoundException;
import com.epam.flightscontroltool.boundary.exception.FlightNotFoundException;
import com.epam.flightscontroltool.boundary.request.FlightRequest;
import com.epam.flightscontroltool.control.repository.AirplaneRepository;
import com.epam.flightscontroltool.control.repository.AirportRepository;
import com.epam.flightscontroltool.control.repository.FlightRepository;
import com.epam.flightscontroltool.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirportRepository airportRepository;

    //TODO: implement SearchRequest instead of many params
    public Page<FlightDto> getFlights(Long departureCityId, Long arrivalCityId, LocalDateTime departureDateTime,
                                      LocalDateTime arrivalDateTime, int pageNumber, int pageSize) {
        if (departureCityId != null && arrivalCityId != null &&
                departureDateTime != null && arrivalDateTime != null) {
            return null; //getFlightsByDirectionAndDate(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, pageNumber, pageSize);
        } else if (departureCityId != null && arrivalCityId != null &&
                departureDateTime == null && arrivalDateTime == null) {
            return null;//getFlightsByDirection(departureCityId, arrivalCityId, pageNumber, pageSize);
        } else {
            return getFlights(pageNumber, pageSize);
        }
    }

    public Page<FlightDto> getFlights(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.flightRepository.findAll(pageRequest)
                .map(FlightDto::from);
    }

    /*public Page<FlightDto> getFlightsByDirection(Long departureCityId, Long arrivalCityId, int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.flightRepository.findByDirection(departureCityId, arrivalCityId, pageRequest)
                .map(FlightDto::from);
    }

    public Page<FlightDto> getFlightsByDirectionAndDate(Long departureCityId, Long arrivalCityId, LocalDateTime departureDateTime,
                                                        LocalDateTime arrivalDateTime, int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.flightRepository.findByDirectionAndDate(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, pageRequest)
                .map(FlightDto::from);
    }*/

    public ExtendedFlightDto getFlight(Long id) {
        return this.flightRepository.findById(id)
                .map(ExtendedFlightDto::from)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Transactional
    public FlightDto create(FlightRequest flightRequest) {
        verifyAirportsAndAirplaneExist(flightRequest);
        var flight = Flight.from(flightRequest);
        flight = this.flightRepository.save(flight);
        return FlightDto.from(flight);
    }

    @Transactional
    public FlightDto update(Long id, FlightRequest flightRequest) {
        verifyAirportsAndAirplaneExist(flightRequest);
        var flight = Flight.from(flightRequest);
        return this.flightRepository.findById(id)
                .map(updated -> updateFlight(flight, updated))
                .map(FlightDto::from)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        this.flightRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteFlight,
                        () -> {
                            throw new FlightNotFoundException(id);
                        });
    }

    private Flight updateFlight(Flight source, Flight target) {
        var airplaneId = source.getAirplane().getId();
        var airplane = this.airplaneRepository.getById(airplaneId);
        target.setAirplane(airplane);

        var departureAirportId = source.getDepartureAirport().getId();
        var departureAirport = this.airportRepository.getById(departureAirportId);
        target.setArrivalAirport(departureAirport);

        var arrivalAirportId = source.getArrivalAirport().getId();
        var arrivalAirport = this.airportRepository.getById(arrivalAirportId);
        target.setDepartureAirport(arrivalAirport);

        target.setArrivalDateTime(source.getArrivalDateTime());
        target.setDepartureDateTime(source.getDepartureDateTime());
        target.setBasePrice(source.getBasePrice());
        return target;
    }

    private void deleteFlight(Flight flight) {
        this.flightRepository.deleteById(flight.getId());
    }

    private void verifyAirportsAndAirplaneExist(FlightRequest flightRequest) {
        var airplaneId = flightRequest.getAirplaneId();
        if (!this.airplaneRepository.existsById(airplaneId)) {
            throw new AirplaneNotFoundException(airplaneId);
        }
        var departureAirportId = flightRequest.getDepartureAirportId();
        if (!this.airportRepository.existsById(departureAirportId)) {
            throw new AirportNotFoundException(departureAirportId);
        }
        var arrivalAirportId = flightRequest.getArrivalAirportId();
        if (!this.airportRepository.existsById(arrivalAirportId)) {
            throw new AirportNotFoundException(arrivalAirportId);
        }
    }
}
