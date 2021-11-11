package com.gym.workout.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Equipment;

public class EquipmentDto {

    private Long id;
    private String name;
    private String date;
    private Integer code;
    private String statusEquipment;
    private Long timeUse;

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.date = equipment.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.code = equipment.getCode();
        this.statusEquipment = equipment.getStatusEquipment().getDescription();
        this.timeUse = ChronoUnit.YEARS.between(equipment.getDate(), LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatusEquipment() {
        return statusEquipment;
    }

    public Long getTimeUse() {
        return timeUse;
    }

    public static Page<EquipmentDto> converter(Page<Equipment> equipments) {
        return equipments.map(EquipmentDto::new);
    }

}
