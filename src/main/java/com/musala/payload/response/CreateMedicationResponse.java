package com.musala.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMedicationResponse {

    private String code;
    private String name;
    private Double weight;
    private String image;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}