package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.AirplaneDto;
import com.epam.flightscontroltool.boundary.exception.AirplaneNotFoundException;
import com.epam.flightscontroltool.boundary.exception.CompanyNotFoundException;
import com.epam.flightscontroltool.boundary.request.AirplaneRequest;
import com.epam.flightscontroltool.control.repository.AirplaneRepository;
import com.epam.flightscontroltool.control.repository.CompanyRepository;
import com.epam.flightscontroltool.entity.Airplane;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final CompanyRepository companyRepository;

    public Page<AirplaneDto> getAirplanes(Long companyId, int pageNumber, int pageSize) {
        return (companyId == null)
                ? getAirplanes(pageNumber, pageSize)
                : getAirplanesByCompanyId(companyId, pageNumber, pageSize);
    }

    public Page<AirplaneDto> getAirplanes(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        var page = this.airplaneRepository.findAll(pageRequest);
        return page.map(AirplaneDto::from);
    }

    public Page<AirplaneDto> getAirplanesByCompanyId(Long companyId, int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        var page = this.airplaneRepository.getAllByCompanyId(companyId, pageRequest);
        return page.map(AirplaneDto::from);
    }

    @Transactional
    public AirplaneDto create(AirplaneRequest airplaneRequest) {
        verifyCompanyExists(airplaneRequest);
        var airplane = Airplane.from(airplaneRequest);
        airplane = this.airplaneRepository.save(airplane);
        return AirplaneDto.from(airplane);
    }

    @Transactional
    public AirplaneDto update(Long id, AirplaneRequest airplaneRequest) {
        verifyCompanyExists(airplaneRequest);
        var airplane = Airplane.from(airplaneRequest);
        return this.airplaneRepository.findById(id)
                .map(updated -> updateAirplane(airplane, updated))
                .map(AirplaneDto::from)
                .orElseThrow(() -> new AirplaneNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        this.airplaneRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteAirplane,
                        () -> {
                            throw new AirplaneNotFoundException(id);
                        }
                );
    }

    private Airplane updateAirplane(Airplane source, Airplane target) {
        var companyId = source.getCompany().getId();
        var company = this.companyRepository.getById(companyId);
        target.setCompany(company);
        target.setNumber(source.getNumber());
        target.setCapacity(source.getCapacity());
        target.setManufacturer(source.getManufacturer());
        return target;
    }

    private void deleteAirplane(Airplane airplane) {
        this.airplaneRepository.deleteById(airplane.getId());
    }

    private void verifyCompanyExists(AirplaneRequest airplaneRequest) {
        var companyId = airplaneRequest.getCompanyId();
        if (!this.companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException(companyId);
        }
    }
}

