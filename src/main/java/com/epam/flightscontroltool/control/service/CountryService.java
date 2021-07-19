package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.CountryDto;
import com.epam.flightscontroltool.boundary.exception.CountryNotFoundException;
import com.epam.flightscontroltool.boundary.request.CountryRequest;
import com.epam.flightscontroltool.control.repository.CountryRepository;
import com.epam.flightscontroltool.entity.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Page<CountryDto> getCountries(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.countryRepository.findAll(pageRequest)
                .map(CountryDto::from);
    }

    @Transactional
    public CountryDto create(CountryRequest countryRequest) {
        var country = Country.from(countryRequest);
        country = this.countryRepository.save(country);
        return CountryDto.from(country);
    }

    @Transactional
    public CountryDto update(Long id, CountryRequest countryRequest) {
        var country = Country.from(countryRequest);
        return this.countryRepository.findById(id)
                .map(updated -> updateCountry(country, updated))
                .map(CountryDto::from)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    // TODO: implement code for deleting a country with connected cities (for now 500 is received, country isn't deleted)
    @Transactional
    public void delete(Long id) {
        this.countryRepository.findById(id)
                .ifPresentOrElse(this::deleteCountry,
                        () -> {
                            throw new CountryNotFoundException(id);
                        });
    }

    private Country updateCountry(Country source, Country target) {
        target.setName(source.getName());
        return target;
    }

    private void deleteCountry(Country country) {
        countryRepository.deleteById(country.getId());
    }
}

