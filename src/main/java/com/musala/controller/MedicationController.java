package com.musala.controller;

import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import com.musala.service.DroneService;
import com.musala.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary="Add Medication Item")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Add New Medication Item",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=CreateMedicationResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @PostMapping(path="/create")
    public CreateMedicationResponse createMedication(@Valid @NotNull @RequestBody CreateMedicationRequest request) {
        return medicationService.createMedication(request);
    }

}
