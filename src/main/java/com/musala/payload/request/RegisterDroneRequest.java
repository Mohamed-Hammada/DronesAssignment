package com.musala.payload.request;

import com.musala.Validator.EnumValidator;
import com.musala.entity.enums.DroneModel;
import com.musala.entity.enums.DroneState;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class RegisterDroneRequest {

    @Size(min=1, message="serial number size must be larger than 0 characters")
    @Size(max=100, message="serial number size must be less than 100 characters")
    @NotBlank(message="serial number should not be empty")
    @NotNull(message="serial number should not be null")
    private String serialNumber;


    @EnumValidator(
            enumClazz=DroneModel.class,
            message="drone model should not be null and have a valid value"
    )
    private String model;

    @NotNull(message="battery should not be null")
    private BigDecimal battery;
}