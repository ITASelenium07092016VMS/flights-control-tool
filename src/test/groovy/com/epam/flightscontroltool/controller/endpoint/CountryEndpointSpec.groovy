package com.epam.flightscontroltool.controller.endpoint

import com.epam.flightscontroltool.boundary.dto.CountryDto
import com.epam.flightscontroltool.entity.Country
import com.epam.flightscontroltool.control.repository.CountryRepository
import com.epam.flightscontroltool.control.service.CountryService
import spock.lang.Specification

class CountryEndpointSpec extends Specification {

    def countryRepository = Mock(CountryRepository)
    def countryService = new CountryService(countryRepository)

    def "should save country mapped from dto"() {
        given: ""
            def countryName = "Test-100"
            def countryDto = new CountryDto(name: countryName)
            def expectedCountry = new Country()

        when: ""
            def result = countryService.create(countryDto)

        then: ""
            1 * countryRepository.save(_ as Country) >> {
                Country country ->
                    assert country.name == countryName
                    expectedCountry
            }

        // should return saved country by repository
        and: ""
            result == expectedCountry

    }
}
