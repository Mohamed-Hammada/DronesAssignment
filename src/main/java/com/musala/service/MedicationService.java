package com.musala.service;

import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.response.CreateMedicationResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface MedicationService {
    CreateMedicationResponse createMedication(@Valid @NotNull @RequestBody CreateMedicationRequest request);
}