package com.musala.service.impl;

import com.musala.constant.GeneralConstants;
import com.musala.entity.Drone;
import com.musala.entity.LoadMedication;
import com.musala.entity.Medication;
import com.musala.entity.enums.DroneModel;
import com.musala.exception.custom.DataNotValid;
import com.musala.exception.custom.ExceedDroneWeight;
import com.musala.exception.custom.LowBatteryLevel;
import com.musala.mapper.DroneMapper;
import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.RegisterDroneResponse;
import com.musala.repository.DroneRepository;
import com.musala.repository.LoadMedicationRepository;
import com.musala.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class DroneServiceImplTest {
    @MockBean
    private DroneRepository droneRepository;
    @MockBean
    private MedicationRepository medicationRepository;

    @MockBean
    private LoadMedicationRepository loadMedicationRepository;
    @Autowired
    private DroneServiceImpl droneService;
    @Autowired
    private GeneralConstants generalConstants;
    @Spy
    DroneMapper droneMapper=Mappers.getMapper(DroneMapper.class);

    private final String code="TUND_OO_0515";
    private final String serialNumber="TUND_OO_0515";
    private final String imageUrl="http//imageurl";
    private final BigDecimal batteryLevel=new BigDecimal(30);
    private final BigDecimal lowBatteryLevel=new BigDecimal(20);

    private final String medicationName="medicationName";
    private final String targetLocationLatitude="targetLocationLatitude";
    private final String sourceLocationLatitude="sourceLocationLatitude";

    @Test
    void registerDrone_SerialNumberAlreadyRegister() {
        RegisterDroneRequest request=getRegisterDroneRequest();
        Drone drone=getDrone();
        given(droneRepository.findById(serialNumber)).willReturn(Optional.of(drone));
        assertThrows(DataNotValid.class, ()->droneService.registerDrone(request));
    }

    @Test
    void registerDrone() {
        RegisterDroneRequest request=getRegisterDroneRequest();
        RegisterDroneResponse response=getRegisterDroneResponse();
        Drone drone=getDrone();
        given(droneRepository.findById(serialNumber)).willReturn(Optional.empty());
        given(droneRepository.save(any())).willReturn(drone);
        given(droneMapper.buildPayload(drone)).willReturn(response);
        assertEquals(droneService.registerDrone(request).getSerialNumber(), serialNumber);
    }


    @Test
    void getLoadedMedicationItem_throwsLowBatteryLevel() {
        Medication medication=getMedication();
        Drone droneLowBatteryLevel=getDroneLowBatteryLevel();
        LoadDroneRequest loadDroneRequest=getLoadDroneRequest();
        given(droneRepository.findById(any())).willReturn(Optional.of(droneLowBatteryLevel));
        given(medicationRepository.findById(any())).willReturn(Optional.of(medication));
        assertThrows(LowBatteryLevel.class, ()->droneService.loadDroneWithMedication(loadDroneRequest));
    }

    @Test
    void getLoadedMedicationItem() {
        Medication medication=getMedication();
        Drone drone=getDrone();
        LoadDroneRequest loadDroneRequest=getLoadDroneRequest();
        given(droneRepository.findById(any())).willReturn(Optional.of(drone));
        given(medicationRepository.findById(any())).willReturn(Optional.of(medication));
        given(loadMedicationRepository.findByDroneSerialNumber(any())).willReturn(new ArrayList<>());
        assertEquals(droneService.loadDroneWithMedication(loadDroneRequest).getSerialNumber(), serialNumber);
    }

    @Test
    void getLoadedMedicationItem_OverWeight() {
        Medication medication=getMedication();
        Drone drone=getDrone();
        LoadMedication loadMedication=getLoadMedication(medication, drone);
        LoadDroneRequest loadDroneRequest=getLoadDroneRequest();
        given(droneRepository.findById(any())).willReturn(Optional.of(drone));
        given(medicationRepository.findById(any())).willReturn(Optional.of(medication));
        given(loadMedicationRepository.findByDroneSerialNumber(any())).willReturn(new ArrayList<LoadMedication>(List.of(loadMedication)));
        assertThrows(ExceedDroneWeight.class, ()->droneService.loadDroneWithMedication(loadDroneRequest));
    }


    @Test
    void getDroneBatteryLevel() {
        Drone drone=getDroneLowBatteryLevel();
        given(droneRepository.findById(serialNumber)).willReturn(Optional.of(drone));
        assertEquals(droneService.getDroneBatteryLevel(serialNumber).getSerialNumber(), serialNumber);
    }

    @Test
    void getDroneBatteryLevel_DataNotValid() {
        given(droneRepository.findById(serialNumber)).willReturn(Optional.empty());
        assertThrows(DataNotValid.class,()->droneService.getDroneBatteryLevel(serialNumber));
    }


    private Drone getDroneLowBatteryLevel() {
        return getDroneWithBatteryLevel(lowBatteryLevel);
    }

    private Drone getDrone() {
        return getDroneWithBatteryLevel(batteryLevel);
    }

    private Drone getDroneWithBatteryLevel(BigDecimal batteryLevel) {
        return Drone.builder().serialNumber(serialNumber).battery(batteryLevel).model(DroneModel.Heavyweight).build();
    }

    private Medication getMedication() {
        return Medication.builder().code(code).image(imageUrl).name(medicationName).weight(400).build();
    }

    private RegisterDroneRequest getRegisterDroneRequest() {
        return RegisterDroneRequest.builder()
                .serialNumber(serialNumber)
                .battery(batteryLevel)
                .build();
    }

    private RegisterDroneResponse getRegisterDroneResponse() {
        return RegisterDroneResponse.builder()
                .serialNumber(serialNumber)
                .battery(batteryLevel)
                .build();
    }

    private LoadDroneRequest getLoadDroneRequest() {
        return LoadDroneRequest.builder()
                .medicationCode(code)
                .droneSerialNumber(serialNumber)
                .destinationLocation(targetLocationLatitude)
                .sourceLocation(sourceLocationLatitude)
                .build();
    }

    private LoadMedication getLoadMedication(Medication medication, Drone drone) {
        return LoadMedication.builder()
                .sourceLocation(sourceLocationLatitude)
                .medication(medication)
                .drone(drone)
                .build();
    }
}