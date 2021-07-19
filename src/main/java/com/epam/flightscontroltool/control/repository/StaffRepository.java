package com.epam.flightscontroltool.control.repository;

import com.epam.flightscontroltool.entity.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffMember, Long> {

    // TODO: add query + implement connection between staff and flight
    /*@Query(value = "", nativeQuery = true)
    Page<StaffMember> getStaffByFlightId(@Param("flightId") Long flightId, Pageable pageable);*/

}