package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.CategoryForm;
import com.gym.workout.dto.CategoryDto;
import com.gym.workout.model.Category;
import com.gym.workout.repository.CategoryRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDto create(CategoryForm categoryForm) {

        Category category = categoryForm.converter();
        category = categoryRepository.save(category);
        return new CategoryDto(category);
    }

    public Page<CategoryDto> findAll(Pageable page) {

        Page<Category> categories = categoryRepository.findAll(page);

        return CategoryDto.converter(categories);
    }

    public CategoryDto findById(Long id) {

        try {
            Category category = categoryRepository.getById(id);
            return new CategoryDto(category);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
