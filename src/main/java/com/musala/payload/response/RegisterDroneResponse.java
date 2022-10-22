package com.musala.payload.response;

import com.musala.entity.enums.DroneModel;
import com.musala.entity.enums.DroneState;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RegisterDroneResponse {
    private String serialNumber;
    private DroneModel model;
    private double currentWeight;
    private BigDecimal battery;
    private DroneState state;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}