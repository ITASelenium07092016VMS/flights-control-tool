package com.epam.flightscontroltool.entity;

import com.epam.flightscontroltool.boundary.request.StaffMemberRequest;
import com.epam.flightscontroltool.control.mapper.StaffMemberMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Position position;

    public static StaffMember from(StaffMemberRequest staffMemberRequest) {
        return StaffMemberMapper.INSTANCE.toStaffMember(staffMemberRequest);
    }
}
