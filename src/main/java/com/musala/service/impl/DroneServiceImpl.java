package com.musala.service.impl;

import com.musala.constant.GeneralConstants;
import com.musala.entity.Drone;
import com.musala.entity.LoadMedication;
import com.musala.entity.Medication;
import com.musala.entity.enums.DroneState;
import com.musala.exception.custom.DataNotFound;
import com.musala.exception.custom.DataNotValid;
import com.musala.exception.custom.ExceedDroneWeight;
import com.musala.exception.custom.LowBatteryLevel;
import com.musala.mapper.DroneMapper;
import com.musala.mapper.MedicationMapper;
import com.musala.payload.request.LoadDroneRequest;
import com.musala.payload.request.RegisterDroneRequest;
import com.musala.payload.response.*;
import com.musala.repository.DroneRepository;
import com.musala.repository.LoadMedicationRepository;
import com.musala.repository.MedicationRepository;
import com.musala.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final LoadMedicationRepository loadMedicationRepository;
    private final DroneMapper droneMapper=DroneMapper.INSTANCE;
    private final MedicationMapper medicationMapper=MedicationMapper.INSTANCE;
    private final GeneralConstants generalConstants;

    @Override
    public RegisterDroneResponse registerDrone(RegisterDroneRequest request) {
        droneRepository.findById(request.getSerialNumber()).ifPresent(e->{
            throw new DataNotValid(generalConstants.getSerialNumberAlreadyExists());
        });
        return droneMapper.buildPayload(droneRepository.save(droneMapper.buildPayload(request)));
    }

    @Transactional
    @Override
    public LoadDroneResponse loadDroneWithMedication(LoadDroneRequest request) {
        // verification and get db data
        Drone drone=droneRepository.findById(request.getDroneSerialNumber()).orElseThrow(()->new DataNotValid(generalConstants.getSerialNumberAlreadyExists()));

        if (new BigDecimal(generalConstants.getLowBatteryLevel()).compareTo(drone.getBattery()) >= 0) {
            throw new LowBatteryLevel(generalConstants.getLowBatteryLevelMessage());
        }

        Medication medication=medicationRepository.findById(request.getMedicationCode()).orElseThrow(()->new DataNotFound(generalConstants.getMedicationNotExists()));
        LoadMedication loadMedicationNew=LoadMedication.builder().destinationLocation(request.getDestinationLocation()).sourceLocation(request.getSourceLocation()).drone(drone).medication(medication).build();

        List<LoadMedication> allLoadedMedication=loadMedicationRepository.findByDroneSerialNumber(request.getDroneSerialNumber());
        allLoadedMedication.add(loadMedicationNew);
        double totalMedicationWeight=getTotalMedicationWeightWithVerification(allLoadedMedication);
        // save to db
        setDroneWeightAndState(drone, totalMedicationWeight);

        saveLoadDroneWithMedication(drone, loadMedicationNew);

        return getLoadDroneResponse(request, allLoadedMedication, totalMedicationWeight);
    }

    private void setDroneWeightAndState(Drone drone, double totalMedicationWeight) {
        drone.setCurrentWeight(totalMedicationWeight);
        if (totalMedicationWeight == generalConstants.getMaxWeightDroneLimit())
            drone.setState(DroneState.LOADED);
        if (totalMedicationWeight < generalConstants.getMaxWeightDroneLimit())
            drone.setState(DroneState.LOADING);
    }

    @Override
    public DroneMedicationLoadResponse getLoadedMedicationItem(String serialNumber) {
        droneRepository.findById(serialNumber).orElseThrow(()->new DataNotValid(generalConstants.getSerialNumberNotExists()));
        List<LoadMedication> allLoadedMedication=loadMedicationRepository.findByDroneSerialNumber(serialNumber);
        double totalMedicationWeight=getTotalMedicationWeightWithVerification(allLoadedMedication);
        return getDroneMedicationLoadResponse(serialNumber, allLoadedMedication, totalMedicationWeight);
    }


    @Override
    public AvailableDroneResponse getAvailableDroneForLoading() {
        return AvailableDroneResponse.builder().drones(droneRepository.findByCurrentWeightLessThan(generalConstants.getMaxWeightDroneLimit())).build();
    }

    @Override
    public DroneBatteryLevelResponse getDroneBatteryLevel(String serialNumber) {
        Drone drone=droneRepository.findById(serialNumber).orElseThrow(()->new DataNotValid(generalConstants.getSerialNumberNotExists()));
        return droneMapper.buildBatteryLevelPayload(drone);
    }

    private void saveLoadDroneWithMedication(Drone drone, LoadMedication loadMedicationNew) {
        loadMedicationRepository.save(loadMedicationNew);
        droneRepository.save(drone);
    }

    private LoadDroneResponse getLoadDroneResponse(LoadDroneRequest request, List<LoadMedication> allLoadedMedication, double totalMedicationWeight) {
        List<Medication> medications=allLoadedMedication.stream().map(e->e.getMedication()).toList();
        return LoadDroneResponse.builder()
                .currentWeight(totalMedicationWeight)
                .serialNumber(request.getDroneSerialNumber())
                .medicationList(medicationMapper.buildPayload(medications))
                .build();
    }

    private double getTotalMedicationWeightWithVerification(List<LoadMedication> allLoadedMedication) {
        double totalMedicationWeight=allLoadedMedication.stream().mapToDouble(e->e.getMedication().getWeight()).sum();
        if (totalMedicationWeight > generalConstants.getMaxWeightDroneLimit()) {
            throw new ExceedDroneWeight(generalConstants.getExceedDroneWeight());
        }
        return totalMedicationWeight;
    }

    private DroneMedicationLoadResponse getDroneMedicationLoadResponse(String serialNumber, List<LoadMedication> allLoadedMedication, double totalMedicationWeight) {
        List<Medication> medications=allLoadedMedication.stream().map(e->e.getMedication()).toList();
        return DroneMedicationLoadResponse.builder()
                .currentWeight(totalMedicationWeight)
                .serialNumber(serialNumber)
                .medicationList(medicationMapper.buildPayload(medications))
                .build();
    }
}
