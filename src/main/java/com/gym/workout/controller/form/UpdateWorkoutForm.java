package com.gym.workout.controller.form;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.gym.workout.model.Workout;
import com.gym.workout.repository.WorkoutRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

public class UpdateWorkoutForm {

    private String nameExercise;
    private Integer loop;
    private Integer weight;
    private Integer series;

    public String getNameExercise() {
        return nameExercise;
    }

    public Integer getLoop() {
        return loop;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getSeries() {
        return series;
    }

    public Workout update(Long id, WorkoutRepository workoutRepository) {

        try {
            Workout work = workoutRepository.getById(id);
            Workout entity = work;

            entity.setNameExercise(this.getNameExercise());
            entity.setLoop(this.getLoop());
            entity.setWeight(this.getWeight());
            entity.setSeries(this.getSeries());
            workoutRepository.save(entity);
            return entity;

        } catch(EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
