package com.musala.mapper;

import com.musala.entity.Medication;
import com.musala.payload.request.CreateMedicationRequest;
import com.musala.payload.response.CreateMedicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy=ReportingPolicy.WARN)
public interface MedicationMapper {
    MedicationMapper INSTANCE=Mappers.getMapper(MedicationMapper.class);

    @Mapping(target="updatedAt", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    Medication buildPayload(CreateMedicationRequest request);

    CreateMedicationResponse buildPayload(Medication response);

    List<CreateMedicationResponse> buildPayload(List<Medication> response);

}
