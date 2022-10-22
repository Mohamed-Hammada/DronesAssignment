package com.musala.service;

import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface DroneService {

    RegisterDroneResponse registerDrone(RegisterDroneRequest request);

    LoadDroneResponse loadDroneWithMedication(LoadDroneRequest request);


    DroneMedicationLoadResponse getLoadedMedicationItem(String serialNumber);


    AvailableDroneResponse getAvailableDroneForLoading();


    DroneBatteryLevelResponse getDroneBatteryLevel(String serialNumber);
}
