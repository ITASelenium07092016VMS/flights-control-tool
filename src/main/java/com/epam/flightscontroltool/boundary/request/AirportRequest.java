package com.epam.flightscontroltool.boundary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AirportRequest {

    @ApiModelProperty(
            value = "airport name",
            dataType = "String",
            example = "Vnukovo",
            required = true)
    @NotNull(message = "Name should be filled in")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(
            value = "city id",
            dataType = "long",
            required = true)
    @JsonProperty("cityId")
    private Long cityId;
}
