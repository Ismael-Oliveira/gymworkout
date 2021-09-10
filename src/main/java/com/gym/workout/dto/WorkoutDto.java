package com.gym.workout.dto;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Card;
import com.gym.workout.model.Workout;

public class WorkoutDto {

    private Long id;
    private String nameExercise;
    private Integer loop;
    private Integer weight;
    private Integer series;

    public WorkoutDto(Workout workout) {
        this.id = workout.getId();
        this.nameExercise = workout.getNameExercise();
        this.loop = workout.getLoop();
        this.weight = workout.getWeight();
        this.series = workout.getSeries();
    }

    public Long getId() {
        return id;
    }

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

    public static Page<WorkoutDto> converter(Page<Workout> workouts) {
        return workouts.map(WorkoutDto::new);
    }
}
