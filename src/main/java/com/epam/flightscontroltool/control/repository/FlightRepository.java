package com.epam.flightscontroltool.control.repository;

import com.epam.flightscontroltool.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // TODO: add proper queries
    /*@Query(value = "", nativeQuery = true)
    Page<Flight> findByDirection(Long departureCityId, Long arrivalCityId, Pageable pageable);

    @Query(value = "", nativeQuery = true)
    Page<Flight> findByDirectionAndDate(Long departureCityId, Long arrivalCityId,
                                        LocalDateTime departureDate, LocalDateTime arrivalDate,
                                        Pageable pageable);*/
}
