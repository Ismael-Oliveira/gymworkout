package com.gym.workout.controller.form;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;

import com.gym.workout.model.Client;
import com.gym.workout.model.Equipment;
import com.gym.workout.model.StatusEquipment;
import com.gym.workout.repository.EquipmentRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

public class UpdateEquipmentForm {

    @NotNull
    private StatusEquipment statusEquipment;

    public UpdateEquipmentForm() {}

    public UpdateEquipmentForm(@NotNull StatusEquipment statusEquipment) {
        this.statusEquipment = statusEquipment;
    }

    public StatusEquipment getStatusEquipment() {
        return statusEquipment;
    }

    public Equipment update(Long id, EquipmentRepository equipmentRepository) {
        try {
            Equipment equipment = equipmentRepository.getById(id);
            Equipment entity = equipment;

            entity.setStatusEquipment(this.getStatusEquipment());
            equipmentRepository.save(entity);
            return entity;

        } catch(EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
