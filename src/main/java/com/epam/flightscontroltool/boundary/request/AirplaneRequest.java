package com.epam.flightscontroltool.boundary.request;

import com.epam.flightscontroltool.entity.Manufacturer;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneRequest {

    @ApiModelProperty(
            value = "airplane number",
            dataType = "String",
            example = "800-443-0478",
            required = true)
    @NotNull(message = "Number should be filled in")
    @JsonProperty("number")
    private String number;

    @ApiModelProperty(
            value = "number of seats (capacity)",
            dataType = "int",
            required = true)
    @JsonProperty("capacity")
    private int capacity;

    @ApiModelProperty(
            value = "airplane company id",
            dataType = "long",
            required = true)
    @JsonProperty("companyId")
    private Long companyId;

    @ApiModelProperty(
            value = "airplane manufacturer name",
            dataType = "String",
            required = true)
    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;

}
