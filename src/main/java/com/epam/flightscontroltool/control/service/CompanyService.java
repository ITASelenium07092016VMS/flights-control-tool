package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.CompanyDto;
import com.epam.flightscontroltool.boundary.exception.CompanyNotFoundException;
import com.epam.flightscontroltool.boundary.request.CompanyRequest;
import com.epam.flightscontroltool.control.repository.CompanyRepository;
import com.epam.flightscontroltool.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Page<CompanyDto> getCompanies(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.companyRepository.findAll(pageRequest)
                .map(CompanyDto::from);
    }

    @Transactional
    public CompanyDto create(CompanyRequest companyRequest) {
        var company = Company.from(companyRequest);
        company = this.companyRepository.save(company);
        return CompanyDto.from(company);
    }

    @Transactional
    public CompanyDto update(Long id, CompanyRequest companyRequest) {
        var company = Company.from(companyRequest);
        return this.companyRepository.findById(id)
                .map(updated -> updateCompany(company, updated))
                .map(CompanyDto::from)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        this.companyRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteCompany,
                        () -> {
                            throw new CompanyNotFoundException(id);
                        }
                );
    }

    private Company updateCompany(Company source, Company target) {
        target.setName(source.getName());
        return target;
    }

    private void deleteCompany(Company company) {
        this.companyRepository.deleteById(company.getId());
    }
}

