package com.musala.repository;

import com.musala.entity.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LoadMedicationRepository extends JpaRepository<LoadMedication, Long> {
    List<LoadMedication> findByDroneSerialNumber(String droneSerialNumber);
}