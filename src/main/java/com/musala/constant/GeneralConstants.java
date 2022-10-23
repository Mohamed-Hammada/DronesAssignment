package com.musala.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class GeneralConstants {

    @Value("${messages.serial.no.exists:Serial Number Already Register}")
    private String serialNumberAlreadyExists;
    @Value("${messages.medication.not.exists:Medication Not Registered}")
    private String medicationNotExists;
    @Value("${messages.serial.no.not.exists:Serial Number Not Registered}")
    private String serialNumberNotExists;
    @Value("${messages.exceed.drone.weight:more weight than the drone can carry}")
    private String exceedDroneWeight;
    @Value("${messages.exceed.drone.length:100}")
    private Double exceedDroneSerialNumberLength;
    @Value("${messages.battery.low:25}")
    private BigDecimal lowBatteryLevel;
    @Value("${messages.battery.low:Low Battery Level}")
    private String lowBatteryLevelMessage;
    @Value("${messages.max.drone.limit.weight:500}")
    private double maxWeightDroneLimit;
}
