package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.AirportRequest;
import com.epam.flightscontroltool.control.mapper.AirportMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public static Airport from(AirportRequest airportRequest) {
        return AirportMapper.INSTANCE.toAirport(airportRequest);
    }
}