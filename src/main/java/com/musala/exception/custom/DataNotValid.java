package com.musala.exception.custom;

public class DataNotValid extends RuntimeException {
    public DataNotValid(String message) {
        super(message);
    }
}
