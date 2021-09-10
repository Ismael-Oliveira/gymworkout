package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
