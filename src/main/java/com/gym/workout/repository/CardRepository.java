package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
