package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

}
