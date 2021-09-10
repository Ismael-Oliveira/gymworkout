package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Client;
import com.gym.workout.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
