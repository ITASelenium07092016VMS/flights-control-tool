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
public class CityRequest {

    @ApiModelProperty(
            value = "city name",
            dataType = "String",
            example = "Milan",
            required = true)
    @NotNull(message = "Name should be filled in")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(
            value = "country id",
            dataType = "long",
            required = true)
    @JsonProperty("countryId")
    private Long countryId;

}
