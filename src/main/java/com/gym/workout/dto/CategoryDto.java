package com.gym.workout.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Category;
import com.gym.workout.model.Client;
import com.gym.workout.model.TypeUser;

public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Page<CategoryDto> converter(Page<Category> categories) {
        return categories.map(CategoryDto::new);
    }
}
