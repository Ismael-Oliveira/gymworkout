package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Card;
import com.gym.workout.model.SpreadSheet;

public interface SpreadSheetRepository extends JpaRepository<SpreadSheet, Long> {
}
