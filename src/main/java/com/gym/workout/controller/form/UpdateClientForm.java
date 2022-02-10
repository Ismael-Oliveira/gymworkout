package com.gym.workout.controller.form;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.gym.workout.model.Client;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

public class UpdateClientForm {

    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Client update(Long id, ClientRepository clientRepository) {

        try {
            Client client = clientRepository.getById(id);
            Client entity = client;

            entity.setEmail(this.getEmail());
            entity.setName(this.getName());
            clientRepository.save(entity);
            return entity;

        } catch(EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
