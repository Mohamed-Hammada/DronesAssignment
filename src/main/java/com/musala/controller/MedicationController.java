package com.musala.controller;

import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import com.musala.service.DroneService;
import com.musala.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @PostMapping(path="/create")
    public CreateMedicationResponse createMedication(@Valid @NotNull @RequestBody CreateMedicationRequest request) {
        return medicationService.createMedication(request);
    }

}
