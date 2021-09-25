package com.gym.workout.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Category;
import com.gym.workout.model.Workout;

public class CategoryDto {

    private Long id;
    private String name;
    private Set<Workout> planning = new HashSet<>();

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.planning = category.getPlanning();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Workout> getPlanning() {
        return planning;
    }

    public static Page<CategoryDto> converter(Page<Category> categories) {
        return categories.map(CategoryDto::new);
    }
}
