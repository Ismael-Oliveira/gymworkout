package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {

}
