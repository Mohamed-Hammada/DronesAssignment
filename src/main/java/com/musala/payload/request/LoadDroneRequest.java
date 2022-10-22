package com.musala.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoadDroneRequest {
    @NotBlank(message="serial number should not be empty")
    @NotNull(message="serial number should not be null")
    private String droneSerialNumber;

    @NotBlank(message="Medication code should not be empty")
    @NotNull(message="Medication code should not be null")
    private String medicationCode;

    @NotBlank(message="source location should not be empty")
    @NotNull(message="source location should not be null")
    private String sourceLocation;

    @NotBlank(message="destination location should not be empty")
    @NotNull(message="destination location should not be null")
    private String destinationLocation;
}
