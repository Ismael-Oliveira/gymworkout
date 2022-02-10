package com.gym.workout.controller.form;

import com.gym.workout.model.Category;
import com.gym.workout.model.Workout;

public class WorkoutForm {

    private String nameExercise;
    private Integer loop;
    private Integer weight;
    private Integer series;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public Workout converter() {
        return new Workout(this.getNameExercise(), this.getLoop(), this.getWeight(), this.getSeries(), this.getCategory());
    }
}
