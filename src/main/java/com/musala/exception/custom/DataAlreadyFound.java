package com.musala.exception.custom;

public class DataAlreadyFound extends RuntimeException {
    public DataAlreadyFound(String message) {
        super(message);
    }
}
