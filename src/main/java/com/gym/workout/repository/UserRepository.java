package com.gym.workout.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.UserGymWorkout;

public interface UserRepository extends JpaRepository<UserGymWorkout, Long> {
    Optional<UserGymWorkout> findByUsername(String username);
}
