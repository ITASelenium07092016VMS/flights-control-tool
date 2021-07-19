package com.epam.flightscontroltool.control.mapper;

import com.epam.flightscontroltool.boundary.dto.StaffMemberDto;
import com.epam.flightscontroltool.boundary.request.StaffMemberRequest;
import com.epam.flightscontroltool.entity.StaffMember;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StaffMemberMapper {

    public static final StaffMemberMapper INSTANCE = Mappers.getMapper(StaffMemberMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "position", source = "position")
    public abstract StaffMemberDto toStaffMemberDto(StaffMember staffMember);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "position", source = "position")
    public abstract StaffMember toStaffMember(StaffMemberRequest staffMemberDto);
}
