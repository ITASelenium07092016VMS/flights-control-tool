package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.CityRequest;
import com.epam.flightscontroltool.control.mapper.CityMapper;
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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Airport> airports = new ArrayList<>();

    public static City from(CityRequest cityRequest) {
        return CityMapper.INSTANCE.toCity(cityRequest);
    }
}