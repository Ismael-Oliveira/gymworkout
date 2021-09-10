package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
