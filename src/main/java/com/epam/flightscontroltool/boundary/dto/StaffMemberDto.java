package com.epam.flightscontroltool.boundary.dto;

import com.epam.flightscontroltool.control.mapper.StaffMemberMapper;
import com.epam.flightscontroltool.entity.Position;
import com.epam.flightscontroltool.entity.StaffMember;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StaffMemberDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("position")
    private Position position;

    public static StaffMemberDto from(StaffMember staffMember) {
        return StaffMemberMapper.INSTANCE.toStaffMemberDto(staffMember);
    }
}
