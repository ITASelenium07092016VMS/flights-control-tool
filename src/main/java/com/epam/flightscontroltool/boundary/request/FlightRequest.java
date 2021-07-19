package com.epam.flightscontroltool.boundary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequest {

    @ApiModelProperty(
            value = "airplane id",
            dataType = "long",
            required = true)
    @NotNull(message = "Airplane id should be filled in")
    @JsonProperty("airplaneId")
    private Long airplaneId;

    @ApiModelProperty(
            value = "departure airport id",
            dataType = "long",
            required = true)
    @JsonProperty("departureAirportId")
    private Long departureAirportId;

    @ApiModelProperty(
            value = "arrival airport id",
            dataType = "long",
            required = true)
    @JsonProperty("arrivalAirportId")
    private Long arrivalAirportId;

    @ApiModelProperty(
            value = "departure date and time",
            dataType = "String",
            required = true)
    @JsonProperty("departureDateTime")
    private LocalDateTime departureDateTime;

    @ApiModelProperty(
            value = "arrival date and time",
            dataType = "String",
            required = true)
    @JsonProperty("arrivalDateTime")
    private LocalDateTime arrivalDateTime;

    @ApiModelProperty(
            value = "base flight price",
            dataType = "number",
            required = true)
    @JsonProperty("basePrice")
    private BigDecimal basePrice;
}
