package com.epam.flightscontroltool.control.repository;

import com.epam.flightscontroltool.entity.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Page<Airplane> getAllByCompanyId(@Param("companyId") Long companyId, Pageable pageable);

}