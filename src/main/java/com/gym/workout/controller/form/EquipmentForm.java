package com.gym.workout.controller.form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.gym.workout.dto.EquipmentDto;
import com.gym.workout.model.Client;
import com.gym.workout.model.Equipment;
import com.gym.workout.model.Register;
import com.gym.workout.repository.RegisterRepository;

public class EquipmentForm {

    @NotNull
    private String name;
    @NotNull
    private Integer code;

    public EquipmentForm(@NotNull String name, @NotNull Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    public Equipment converter() {
        return new Equipment(this.getName(), this.getCode());
    }
}
