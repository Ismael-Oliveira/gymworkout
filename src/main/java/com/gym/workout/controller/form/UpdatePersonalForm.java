package com.gym.workout.controller.form;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.gym.workout.model.Personal;
import com.gym.workout.repository.PersonalRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

public class UpdatePersonalForm {

    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Personal update(Long id, PersonalRepository personalRepository) {

        try {
            Personal personal = personalRepository.getById(id);
            Personal entity = personal;

            entity.setEmail(this.getEmail());
            entity.setName(this.getName());
            personalRepository.save(entity);
            return entity;

        } catch(EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
