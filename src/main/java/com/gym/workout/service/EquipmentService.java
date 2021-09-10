package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.ClientForm;
import com.gym.workout.controller.form.EquipmentForm;
import com.gym.workout.controller.form.UpdateClientForm;
import com.gym.workout.controller.form.UpdateEquipmentForm;
import com.gym.workout.dto.ClientDto;
import com.gym.workout.dto.EquipmentDto;
import com.gym.workout.model.Client;
import com.gym.workout.model.Equipment;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.EquipmentRepository;
import com.gym.workout.repository.RegisterRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public EquipmentDto create(EquipmentForm equipmentForm) {

        Equipment equipment = equipmentForm.converter();
        equipment = equipmentRepository.save(equipment);
        return new EquipmentDto(equipment);
    }

    public Page<EquipmentDto> findAll(Pageable page) {

        Page<Equipment> equipments = equipmentRepository.findAll(page);

        return EquipmentDto.converter(equipments);
    }

    public EquipmentDto findById(Long id) {

        try {
            Equipment equipment = equipmentRepository.getById(id);
            return new EquipmentDto(equipment);

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }

    public EquipmentDto update(Long id, UpdateEquipmentForm updateEquipmentForm) {
        Equipment equipment = updateEquipmentForm.update(id, equipmentRepository);
        return new EquipmentDto(equipment);
    }

    public void delete(Long id) {
        try {
            equipmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
