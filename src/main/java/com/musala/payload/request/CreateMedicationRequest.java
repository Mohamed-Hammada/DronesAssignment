package com.musala.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMedicationRequest {

    @NotNull
    @NotBlank
    @Pattern(regexp="[A-Z0-9\\_]+", message="allowed only upper case letters, underscore and numbers")
    private String code;

    @NotNull
    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9\\_\\-]+", message="allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @NotNull
    @Range(min=0, max=500, message="500 g of medication is the maximum weight that drones can carry.")
    private double weight;

    @NotNull
    @NotBlank
    private String image;
}