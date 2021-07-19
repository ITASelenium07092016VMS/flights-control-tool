package com.epam.flightscontroltool.boundary.request;

import com.epam.flightscontroltool.entity.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class StaffMemberRequest {

    @ApiModelProperty(
            value = "staff member first name",
            dataType = "String",
            example = "Marta",
            required = true)
    @NotNull(message = "First name should be filled in")
    @JsonProperty("firstName")
    private String firstName;

    @ApiModelProperty(
            value = "staff member surname",
            dataType = "String",
            example = "Viarenchykava",
            required = true)
    @NotNull(message = "Surname should be filled in")
    @JsonProperty("surname")
    private String surname;

    @ApiModelProperty(
            value = "staff member position",
            dataType = "String",
            example = "Engineer")
    @JsonProperty("position")
    private Position position;
}
