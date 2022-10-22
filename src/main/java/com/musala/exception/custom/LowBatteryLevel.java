package com.musala.exception.custom;

public class LowBatteryLevel extends RuntimeException {
    public LowBatteryLevel(String message) {
        super(message);
    }
}
