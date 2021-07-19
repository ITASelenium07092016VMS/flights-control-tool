package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.CompanyRequest;
import com.epam.flightscontroltool.control.mapper.CompanyMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Airplane> airplanes = new ArrayList<>();

    public static Company from(CompanyRequest companyRequest) {
        return CompanyMapper.INSTANCE.toCompany(companyRequest);
    }
}