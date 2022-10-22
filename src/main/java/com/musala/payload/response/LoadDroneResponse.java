package com.musala.payload.response;

import com.musala.entity.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoadDroneResponse {

    private String serialNumber;
    private double currentWeight;
    private List<CreateMedicationResponse> medicationList;
}
