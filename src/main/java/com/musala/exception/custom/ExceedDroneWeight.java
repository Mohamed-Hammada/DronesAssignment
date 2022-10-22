package com.musala.exception.custom;

public class ExceedDroneWeight extends RuntimeException {
    public ExceedDroneWeight(String message) {
        super(message);
    }
}
