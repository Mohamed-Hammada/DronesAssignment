package com.musala.mapper;

import com.musala.entity.Drone;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.DroneBatteryLevelResponse;
import com.musala.payload.response.RegisterDroneResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy=ReportingPolicy.WARN)
public interface DroneMapper {
    DroneMapper INSTANCE=Mappers.getMapper(DroneMapper.class);

    @Mapping(target="createdAt",ignore=true)
    @Mapping(target="updatedAt",ignore=true)
    @Mapping(target="state",ignore=true)
    @Mapping(target="currentWeight",ignore=true)
    Drone buildPayload(RegisterDroneRequest request);
    RegisterDroneResponse buildPayload(Drone response);
    DroneBatteryLevelResponse buildBatteryLevelPayload(Drone request);
}
