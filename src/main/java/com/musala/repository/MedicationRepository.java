package com.musala.repository;

import com.musala.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MedicationRepository extends JpaRepository<Medication, String> {
}