package com.epam.flightscontroltool.control.repository;

import com.epam.flightscontroltool.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Page<Airport> getAllByCityId(@Param("cityId") Long cityId, Pageable pageable);

}
