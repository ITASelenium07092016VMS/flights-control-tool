package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.CityDto;
import com.epam.flightscontroltool.boundary.dto.ExtendedCityDto;
import com.epam.flightscontroltool.boundary.exception.CityNotFoundException;
import com.epam.flightscontroltool.boundary.exception.CountryNotFoundException;
import com.epam.flightscontroltool.boundary.request.CityRequest;
import com.epam.flightscontroltool.control.repository.CityRepository;
import com.epam.flightscontroltool.control.repository.CountryRepository;
import com.epam.flightscontroltool.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public Page<CityDto> getCities(Long countryId, int pageNumber, int pageSize) {
        return (countryId == null)
                ? getCities(pageNumber, pageSize)
                : getCitiesByCountryId(countryId, pageNumber, pageSize);
    }

    public Page<CityDto> getCities(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return this.cityRepository.findAll(pageRequest)
                .map(CityDto::from);
    }

    public Page<CityDto> getCitiesByCountryId(Long countryId, int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return this.cityRepository.getAllByCountryId(countryId, pageRequest)
                .map(CityDto::from);
    }

    public ExtendedCityDto getCity(Long id) {
        return this.cityRepository.findById(id)
                .map(ExtendedCityDto::from)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public CityDto create(CityRequest cityRequest) {
        verifyCountryExists(cityRequest);
        var city = City.from(cityRequest);
        city = this.cityRepository.save(city);
        return CityDto.from(city);
    }

    @Transactional
    public CityDto update(Long id, CityRequest cityRequest) {
        verifyCountryExists(cityRequest);
        var city = City.from(cityRequest);
        return this.cityRepository.findById(id)
                .map(updated -> updateCity(city, updated))
                .map(CityDto::from)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        this.cityRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteCity,
                        () -> {
                            throw new CityNotFoundException(id);
                        }
                );
    }

    private City updateCity(City source, City target) {
        var countryId = source.getCountry().getId();
        var country = this.countryRepository.getById(countryId);
        target.setCountry(country);
        target.setName(source.getName());
        return target;
    }

    private void deleteCity(City city) {
        this.cityRepository.deleteById(city.getId());
    }

    private void verifyCountryExists(CityRequest cityRequest) {
        var countryId = cityRequest.getCountryId();
        if (!this.countryRepository.existsById(countryId)) {
            throw new CountryNotFoundException(countryId);
        }
    }
}

