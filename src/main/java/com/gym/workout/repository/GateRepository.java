package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.GateOnline;
import com.gym.workout.model.Register;

public interface GateRepository extends JpaRepository<GateOnline, Integer> {

}
