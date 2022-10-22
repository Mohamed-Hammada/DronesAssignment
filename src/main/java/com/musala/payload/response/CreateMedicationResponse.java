package com.musala.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMedicationResponse {

    private String code;
    private String name;
    private Double weight;
    private String image;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}