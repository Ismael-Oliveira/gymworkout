package com.gym.workout.controller.form;

import com.gym.workout.model.Category;

public class CategoryForm {

    private String name;

    public String getName() {
        return name;
    }

    public Category converter() {
        return new Category(this.getName());
    }
}
