package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.UpdateWorkoutForm;
import com.gym.workout.controller.form.WorkoutForm;
import com.gym.workout.dto.WorkoutDto;
import com.gym.workout.model.Workout;
import com.gym.workout.repository.WorkoutRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    public WorkoutDto create(WorkoutForm workoutForm) {

        Workout workout = workoutForm.converter();
        workout = workoutRepository.save(workout);
        return new WorkoutDto(workout);
    }

    public Page<WorkoutDto> findAll(Pageable page) {

        Page<Workout> workouts = workoutRepository.findAll(page);

        return WorkoutDto.converter(workouts);
    }

    public WorkoutDto findById(Long id) {

        try {
            Workout workout = workoutRepository.getById(id);
            return new WorkoutDto(workout);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

    public WorkoutDto update(Long id, UpdateWorkoutForm updateWorkoutForm) {
        Workout workout = updateWorkoutForm.update(id, workoutRepository);
        return new WorkoutDto(workout);
    }

    public void delete(Long id) {
        try {
            workoutRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
