package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.CountryRequest;
import com.epam.flightscontroltool.control.mapper.CountryMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities = new ArrayList<>();

    public static Country from(CountryRequest countryRequest) {
        return CountryMapper.INSTANCE.toCountry(countryRequest);
    }
}
