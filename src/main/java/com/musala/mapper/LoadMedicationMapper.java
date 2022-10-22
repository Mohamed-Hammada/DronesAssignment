package com.musala.mapper;

import com.musala.entity.LoadMedication;
import com.musala.entity.Medication;
import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.response.CreateMedicationResponse;
import com.musala.payload.response.LoadDroneResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy=ReportingPolicy.WARN)
public interface LoadMedicationMapper {
    LoadMedicationMapper INSTANCE=Mappers.getMapper(LoadMedicationMapper.class);
}
