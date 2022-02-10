package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.PerformanceForm;
import com.gym.workout.dto.PerformanceDto;
import com.gym.workout.model.Client;
import com.gym.workout.model.Performance;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.PerformanceRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class PerformanceService {

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    ClientRepository clientRepository;

    public PerformanceDto create(PerformanceForm performanceForm) {

        Performance performance = performanceForm.converter();
        performance = performanceRepository.save(performance);

        Client client = clientRepository.getById(performanceForm.getIdUser());
        client.getPerformances().add(performance);
        clientRepository.save(client);

        return new PerformanceDto(performance);
    }

    public Page<PerformanceDto> findAll(Pageable page) {

        Page<Performance> performances = performanceRepository.findAll(page);

        return PerformanceDto.converter(performances);
    }

    public PerformanceDto findById(Long id) {

        try {
            Performance personal = performanceRepository.getById(id);
            return new PerformanceDto(personal);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

}
