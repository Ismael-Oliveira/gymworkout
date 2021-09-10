package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.PersonalForm;
import com.gym.workout.controller.form.UpdatePersonalForm;
import com.gym.workout.dto.PersonalDto;
import com.gym.workout.model.Personal;
import com.gym.workout.repository.PersonalRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    public PersonalDto create(PersonalForm personalForm) {

        Personal personal = personalForm.converter();
        personal = personalRepository.save(personal);
        return new PersonalDto(personal);
    }

    public Page<PersonalDto> findAll(Pageable page) {

        Page<Personal> personal = personalRepository.findAll(page);

        return PersonalDto.converter(personal);
    }

    public PersonalDto findById(Long id) {

        try {
            Personal personal = personalRepository.getById(id);
            return new PersonalDto(personal);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

    public PersonalDto update(Long id, UpdatePersonalForm updatePersonalForm) {
        Personal personal = updatePersonalForm.update(id, personalRepository);
        return new PersonalDto(personal);
    }

    public void delete(Long id) {
        try {
            personalRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
