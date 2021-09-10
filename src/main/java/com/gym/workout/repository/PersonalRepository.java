package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Long> {

}
