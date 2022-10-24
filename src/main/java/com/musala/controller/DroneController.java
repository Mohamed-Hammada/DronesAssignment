package com.musala.controller;

import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import com.musala.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/drone")
@Tag(name="Drone Controller", description="Drone Controller REST APIs")
public class DroneController {
    private final DroneService droneService;

    @Operation(summary="Register Drone")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Register New Drone",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=RegisterDroneResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @PostMapping(path="/register")
    public RegisterDroneResponse registerDrone(@Valid @NotNull @RequestBody RegisterDroneRequest request) {
        return droneService.registerDrone(request);
    }

    @Operation(summary="Load Drone")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Load Drone with Medication",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=LoadDroneResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @PostMapping(path="/load")
    public LoadDroneResponse loadDroneWithMedication(@Valid @NotNull @RequestBody LoadDroneRequest request) {
        return droneService.loadDroneWithMedication(request);
    }

    @Operation(summary="Get Loaded Drone Medication Items")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Get Loaded Drone Medication Items",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=DroneMedicationLoadResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="404", description="Serial Number not found",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @GetMapping(path="/medications/{serialNumber}")
    public DroneMedicationLoadResponse getLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
        return droneService.getLoadedMedicationItem(serialNumber);
    }

    @Operation(summary="Get Available Drones")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Get Available Drones",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=AvailableDroneResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @GetMapping(path="/available")
    public AvailableDroneResponse getAvailableDroneForLoading() {
        return droneService.getAvailableDroneForLoading();
    }

    @Operation(summary="Get Drone Battery Level")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Get Drone Battery Level",
                    content={@Content(mediaType="application/json",
                            schema=@Schema(implementation=DroneBatteryLevelResponse.class))}),
            @ApiResponse(responseCode="4XX", description="Client Error Invalid Data",
                    content=@Content),
            @ApiResponse(responseCode="404", description="Serial Number not found",
                    content=@Content),
            @ApiResponse(responseCode="5XX", description="Server Error Invalid Data",
                    content=@Content)})
    @GetMapping(path="/battery/{serialNumber}")
    public DroneBatteryLevelResponse getDroneBatteryLevel(@PathVariable("serialNumber") String serialNumber) {
        return droneService.getDroneBatteryLevel(serialNumber);
    }
}
