package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.CompanyMapper;
import com.epam.flightscontroltool.entity.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public static CompanyDto from(Company company) {
        return CompanyMapper.INSTANCE.toCompanyDto(company);
    }
}
