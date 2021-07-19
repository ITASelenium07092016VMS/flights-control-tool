package com.epam.flightscontroltool.control.service;

import com.epam.flightscontroltool.boundary.dto.StaffMemberDto;
import com.epam.flightscontroltool.boundary.exception.StaffMemberNotFoundException;
import com.epam.flightscontroltool.boundary.request.StaffMemberRequest;
import com.epam.flightscontroltool.control.repository.StaffRepository;
import com.epam.flightscontroltool.entity.StaffMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StaffMemberService {

    private final StaffRepository staffRepository;

    public Page<StaffMemberDto> getStaffMembers(Long airplaneId, int pageNumber, int pageSize) {
        return (airplaneId == null)
                ? getStaffMembers(pageNumber, pageSize)
                : getStaffMembersByFlightId(airplaneId, pageNumber, pageSize);
    }

    public Page<StaffMemberDto> getStaffMembers(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.staffRepository.findAll(pageRequest)
                .map(StaffMemberDto::from);
    }

    public Page<StaffMemberDto> getStaffMembersByFlightId(Long flightId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        //return staffRepository.getStaffByFlightId(flightId, pageRequest).map(StaffMemberDto::from);
        return null;
    }

    @Transactional
    public StaffMemberDto create(StaffMemberRequest staffMemberRequest) {
        var staffMember = StaffMember.from(staffMemberRequest);
        staffMember = this.staffRepository.save(staffMember);
        return StaffMemberDto.from(staffMember);
    }

    @Transactional
    public StaffMemberDto update(Long id, StaffMemberRequest staffMemberRequest) {
        var staffMember = StaffMember.from(staffMemberRequest);
        return this.staffRepository.findById(id)
                .map(updated -> updateStaffMember(staffMember, updated))
                .map(StaffMemberDto::from)
                .orElseThrow(() -> new StaffMemberNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        this.staffRepository.findById(id)
                .ifPresentOrElse(
                        this::deleteStaffMember,
                        () -> {
                            throw new StaffMemberNotFoundException(id);
                        }
                );
    }

    private StaffMember updateStaffMember(StaffMember source, StaffMember target) {
        target.setFirstName(source.getFirstName());
        target.setSurname(source.getSurname());
        target.setPosition(source.getPosition());
        return target;
    }

    private void deleteStaffMember(StaffMember staffMember) {
        this.staffRepository.deleteById(staffMember.getId());
    }
}

