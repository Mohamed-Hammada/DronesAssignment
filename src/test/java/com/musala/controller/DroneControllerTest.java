package com.musala.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.musala.constant.GeneralConstants;
import com.musala.entity.Drone;
import com.musala.entity.LoadMedication;
import com.musala.entity.Medication;
import com.musala.entity.enums.DroneModel;
import com.musala.mapper.DroneMapper;
import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.RegisterDroneResponse;
import com.musala.repository.DroneRepository;
import com.musala.repository.LoadMedicationRepository;
import com.musala.repository.MedicationRepository;
import com.musala.service.impl.DroneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DroneControllerTest {
    @MockBean
    DroneController droneController;
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

    @Autowired
    MockMvc mockMvc;
    private final String code="TUND_OO_0515";
    private final String serialNumber="TUND_OO_0515";
    private final String imageUrl="http//imageurl";
    private final BigDecimal batteryLevel=new BigDecimal(30);
    private final BigDecimal lowBatteryLevel=new BigDecimal(20);

    private final String medicationName="medicationName";
    private final String targetLocationLatitude="targetLocationLatitude";
    private final String sourceLocationLatitude="sourceLocationLatitude";
    private final double weight=400;

    @Test
    void registerDrone() throws Exception {
        RegisterDroneRequest registerDroneRequest=getRegisterDroneRequest();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        when(droneService.registerDrone(registerDroneRequest)).thenReturn(getRegisterDroneResponse());
        mockMvc.perform(post("/api/v1/drone/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(registerDroneRequest))).
                andExpect(status().isOk());
    }

    @Test
    void registerDrone_serialNumber_max100char() throws Exception {
        RegisterDroneRequest registerDroneRequest=RegisterDroneRequest.builder().serialNumber("a".repeat(102))
                .battery(batteryLevel)
                .model(DroneModel.Heavyweight.name())
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        when(droneService.registerDrone(registerDroneRequest)).thenReturn(getRegisterDroneResponse());
        mockMvc.perform(post("/api/v1/drone/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(registerDroneRequest)))
                .andExpect(status().is4xxClientError())
                .andExpect(status().reason(containsString("serial number size must be less than 100 characters")))
        ;
    }

    @Test
    void registerDrone_serialNumber_notBlank() throws Exception {
        RegisterDroneRequest registerDroneRequest=RegisterDroneRequest.builder().serialNumber("")
                .battery(batteryLevel)
                .model(DroneModel.Heavyweight.name())
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        when(droneService.registerDrone(registerDroneRequest)).thenReturn(getRegisterDroneResponse());
        String errorMessage=mockMvc.perform(post("/api/v1/drone/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(registerDroneRequest)))
                .andExpect(status().is4xxClientError()).andReturn().getResolvedException().getMessage();
        Assertions.assertTrue(errorMessage.contains("serial number should not be empty"));
    }

    @Test
    void registerDrone_serialNumber_notNull() throws Exception {
        RegisterDroneRequest registerDroneRequest=RegisterDroneRequest.builder().serialNumber(null)
                .battery(batteryLevel)
                .model(DroneModel.Heavyweight.name())
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        when(droneService.registerDrone(registerDroneRequest)).thenReturn(getRegisterDroneResponse());
        String errorMessage=mockMvc.perform(post("/api/v1/drone/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(registerDroneRequest)))
                .andExpect(status().is4xxClientError()).andReturn().getResolvedException().getMessage();
        Assertions.assertTrue(errorMessage.contains("serial number should not be null"));
    }

    @Test
    void loadDroneWithMedication() {
    }

    @Test
    void getLoadedMedicationItem() {
    }

    @Test
    void getAvailableDroneForLoading() {
    }

    @Test
    void getDroneBatteryLevel() {
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
        return Medication.builder().code(code).image(imageUrl).name(medicationName).weight(weight).build();
    }

    private RegisterDroneRequest getRegisterDroneRequest() {
        return RegisterDroneRequest.builder()
                .serialNumber(serialNumber)
                .battery(batteryLevel)
                .model(DroneModel.Heavyweight.name())
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