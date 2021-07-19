package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.AirportDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedAirportDto;
import com.epam.flightscontroltool.boundary.exception.AirportNotFoundException;
import com.epam.flightscontroltool.boundary.exception.CityNotFoundException;
import com.epam.flightscontroltool.boundary.request.AirportRequest;
import com.epam.flightscontroltool.control.repository.AirportRepository;
import com.epam.flightscontroltool.control.repository.CityRepository;
import com.epam.flightscontroltool.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public Page<AirportDto> getAirports(Long cityId, int pageNumber, int pageSize) {
        return (cityId == null)
                ? getAirports(pageNumber, pageSize)
                : getAirportsByCityId(cityId, pageNumber, pageSize);
    }

    public Page<AirportDto> getAirports(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return this.airportRepository.findAll(pageRequest)
                .map(AirportDto::from);
    }

    public Page<AirportDto> getAirportsByCityId(Long cityId, int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return this.airportRepository.getAllByCityId(cityId, pageRequest)
                .map(AirportDto::from);
    }

    public ExtendedAirportDto getAirport(Long id) {
        return this.airportRepository.findById(id)
                .map(ExtendedAirportDto::from)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    @Transactional
    public AirportDto create(AirportRequest airportRequest) {
        verifyCityExists(airportRequest);
        var airport = Airport.from(airportRequest);
        airport = airportRepository.save(airport);
        return AirportDto.from(airport);
    }

    @Transactional
    public AirportDto update(Long id, AirportRequest airportRequest) {
        verifyCityExists(airportRequest);
        var airport = Airport.from(airportRequest);
        return this.airportRepository.findById(id)
                .map(updated -> updateAirport(airport, updated))
                .map(AirportDto::from)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    public void delete(Long id) {
        this.airportRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteAirport,
                        () -> {
                            throw new AirportNotFoundException(id);
                        }
                );
    }

    private Airport updateAirport(Airport source, Airport target) {
        var cityId = source.getCity().getId();
        var city = cityRepository.getById(cityId);
        target.setCity(city);
        target.setName(source.getName());
        return target;
    }

    private void deleteAirport(Airport airport) {
        this.airportRepository.deleteById(airport.getId());
    }

    private void verifyCityExists(AirportRequest airportRequest) {
        var cityId = airportRequest.getCityId();
        if (!this.cityRepository.existsById(cityId)) {
            throw new CityNotFoundException(cityId);
        }
    }
}

