package com.musala.exception.custom;

public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}
