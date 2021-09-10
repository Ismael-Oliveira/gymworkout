package com.gym.workout.controller.form;

import com.gym.workout.model.Workout;

public class WorkoutForm {

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

    public Workout converter() {
        return new Workout(this.getNameExercise(), this.getLoop(), this.getWeight(), this.getSeries());
    }
}