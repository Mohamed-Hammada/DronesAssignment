package com.musala.repository;

import com.musala.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DroneRepository extends JpaRepository<Drone, String> {
   List<Drone> findByCurrentWeightLessThan(double weight);
}