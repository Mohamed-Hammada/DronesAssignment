package com.musala.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneMedicationLoadResponse {
    private String serialNumber;
    private double currentWeight;
    private List<CreateMedicationResponse> medicationList;
}
