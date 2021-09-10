package com.gym.workout.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.ClientForm;
import com.gym.workout.controller.form.UpdateClientForm;
import com.gym.workout.dto.ClientDto;
import com.gym.workout.model.Client;
import com.gym.workout.model.Register;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.RegisterRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RegisterRepository registerRepository;

    public ClientDto create(ClientForm clientForm) {

        Client client = clientForm.converter(registerRepository);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    public Page<ClientDto> findAll(Pageable page) {

        Page<Client> clients = clientRepository.findAll(page);

        return ClientDto.converter(clients);
    }

    public ClientDto findById(Long id) {

        try {
            Client client = clientRepository.getById(id);
            return new ClientDto(client);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

    public ClientDto update(Long id, UpdateClientForm updateClientForm) {
        Client client = updateClientForm.update(id, clientRepository);
        return new ClientDto(client);
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ClientDto getByRegister(Integer registerClient) {
        try {
            Client client = clientRepository.getByRegister(registerClient);
            return new ClientDto(client);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(registerClient);
        }
    }
}
