package com.musala.controller;

import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import com.musala.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/drone")
public class DroneController {
    private final DroneService droneService;

    @PostMapping(path="/register")
    public RegisterDroneResponse registerDrone(@Valid @NotNull @RequestBody RegisterDroneRequest request) {
        return droneService.registerDrone(request);
    }

    @PostMapping(path="/load")
    public LoadDroneResponse loadDroneWithMedication(@Valid @NotNull @RequestBody LoadDroneRequest request) {
        return droneService.loadDroneWithMedication(request);
    }

    @GetMapping(path="/medications/{serialNumber}")
    public DroneMedicationLoadResponse getLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
        return droneService.getLoadedMedicationItem(serialNumber);
    }

    @GetMapping(path="/available")
    public AvailableDroneResponse getAvailableDroneForLoading() {
        return droneService.getAvailableDroneForLoading();
    }

    @GetMapping(path="/battery/{serialNumber}")
    public DroneBatteryLevelResponse getDroneBatteryLevel(@PathVariable("serialNumber") String serialNumber) {
        return droneService.getDroneBatteryLevel(serialNumber);
    }
}
