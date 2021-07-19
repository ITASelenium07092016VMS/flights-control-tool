package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.CompanyDto;
import com.epam.flightscontroltool.boundary.request.CompanyRequest;
import com.epam.flightscontroltool.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CompanyMapper {

    public static final CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract CompanyDto toCompanyDto(Company company);

    @Mapping(target = "name", source = "name")
    public abstract Company toCompany(CompanyRequest companyRequest);
}