package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.ClientForm;
import com.gym.workout.controller.form.UpdateClientForm;
import com.gym.workout.controller.form.UpdateSpreadSheetForm;
import com.gym.workout.dto.ClientDto;
import com.gym.workout.model.Client;
import com.gym.workout.model.UserGymWorkout;
import com.gym.workout.repository.CardRepository;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.RegisterRepository;
import com.gym.workout.repository.SpreadSheetRepository;
import com.gym.workout.repository.UserRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    SpreadSheetRepository spreadSheetRepository;

    public ClientDto create(ClientForm clientForm) {
        boolean exists = clientRepository.existsByEmail(clientForm.getEmail());
        if(exists) {
            throw new DatabaseException("Tente outro! " + clientForm.getEmail() + " já esta cadastrado.");
        }

        Client client = clientForm.converter(registerRepository);
        client = clientRepository.save(client);
        saveUserGym(client);
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

    public ClientDto updateSpreadSheet(Long id, UpdateSpreadSheetForm updateSpreadSheetForm) {
        Client client = updateSpreadSheetForm.update(id, clientRepository, cardRepository, spreadSheetRepository);
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

    private void saveUserGym(Client client) {
        UserGymWorkout user = new UserGymWorkout(client.getEmail(), client.getPassword(), client.getTypeUser(), client.getId());
        userRepository.save(user);
    }

}
