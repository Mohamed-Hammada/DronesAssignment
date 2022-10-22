package com.musala.service.impl;

import com.musala.constant.GeneralConstants;
import com.musala.exception.custom.DataNotFound;
import com.musala.mapper.MedicationMapper;
import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.response.CreateMedicationResponse;
import com.musala.repository.MedicationRepository;
import com.musala.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper=MedicationMapper.INSTANCE;
    private final GeneralConstants generalConstants;

    @Override
    public CreateMedicationResponse createMedication(CreateMedicationRequest request) {
        medicationRepository.findById(request.getCode()).ifPresent(e->{
            new DataNotFound(generalConstants.getMedicationNotExists());
        });
        return medicationMapper.buildPayload(medicationRepository.save(medicationMapper.buildPayload(request)));
    }
}
