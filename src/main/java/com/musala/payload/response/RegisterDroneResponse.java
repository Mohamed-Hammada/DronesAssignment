package com.musala.payload.response;

import com.musala.entity.enums.DroneModel;
import com.musala.entity.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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