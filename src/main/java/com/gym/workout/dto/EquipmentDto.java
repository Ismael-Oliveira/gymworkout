package com.gym.workout.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Equipment;
import com.gym.workout.model.StatusEquipment;

public class EquipmentDto {

    private Long id;
    private String name;
    private LocalDate date;
    private Integer code;
    private StatusEquipment statusEquipment;

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.date = equipment.getDate();
        this.code = equipment.getCode();
        this.statusEquipment = equipment.getStatusEquipment();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getCode() {
        return code;
    }

    public StatusEquipment getStatusEquipment() {
        return statusEquipment;
    }

    public static Page<EquipmentDto> converter(Page<Equipment> equipments) {
        return equipments.map(EquipmentDto::new);
    }

}
