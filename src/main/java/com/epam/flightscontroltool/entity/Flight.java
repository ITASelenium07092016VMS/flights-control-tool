package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.FlightRequest;
import com.epam.flightscontroltool.control.mapper.FlightMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    //private Set<StaffMember> crew;

    public static Flight from(FlightRequest flightRequest) {
        return FlightMapper.INSTANCE.toFlight(flightRequest);
    }
}
