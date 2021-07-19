package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.AirplaneRequest;
import com.epam.flightscontroltool.control.mapper.AirplaneMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(value = EnumType.STRING)
    private Manufacturer manufacturer;

    public static Airplane from(AirplaneRequest airplaneRequest) {
        return AirplaneMapper.INSTANCE.toAirplane(airplaneRequest);
    }
}