package com.musala.service.impl;

import com.musala.constant.GeneralConstants;
import com.musala.entity.Medication;
import com.musala.exception.custom.DataAlreadyFound;
import com.musala.mapper.MedicationMapper;
import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.response.CreateMedicationResponse;
import com.musala.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class MedicationServiceImplTest {

    @MockBean
    private MedicationRepository medicationRepository;
    @Autowired
    private MedicationServiceImpl medicationService;
    @Autowired
    private GeneralConstants generalConstants;

    @Spy
    MedicationMapper medicationMapper=Mappers.getMapper(MedicationMapper.class);

    private final String code="TUND_OO_0515";
    private final String imageUrl="http//imageurl";

    private final String medicationName="medicationName";
    private final double weight=400;


    @Test
    void createMedication() {
        Medication medication=getMedication();
        CreateMedicationRequest createMedicationRequest=getCreateMedicationRequest();
        CreateMedicationResponse createMedicationResponse=getCreateMedicationResponse();
        given(medicationRepository.findById(code)).willReturn(Optional.empty());
        given(medicationRepository.save(any())).willReturn(medication);
        given(medicationMapper.buildPayload(medication)).willReturn(createMedicationResponse);
        assertEquals(medicationService.createMedication(createMedicationRequest).getCode(), medication.getCode());
    }

    @Test
    void createMedication_DataAlreadyFound() {
        Medication medication=getMedication();
        CreateMedicationRequest createMedicationRequest=getCreateMedicationRequest();
        given(medicationRepository.findById(code)).willReturn(Optional.of(medication));
        assertThrows(DataAlreadyFound.class, ()->medicationService.createMedication(createMedicationRequest).getCode());
    }

    private Medication getMedication() {
        return Medication.builder().code(code).image(imageUrl).name(medicationName).weight(weight).build();
    }

    private CreateMedicationRequest getCreateMedicationRequest() {
        return CreateMedicationRequest.builder().code(code).image(imageUrl).name(medicationName).weight(weight).build();
    }

    private CreateMedicationResponse getCreateMedicationResponse() {
        return CreateMedicationResponse.builder().code(code).image(imageUrl).name(medicationName).weight(weight).build();
    }
}