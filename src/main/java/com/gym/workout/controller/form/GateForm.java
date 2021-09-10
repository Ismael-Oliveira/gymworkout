package com.gym.workout.controller.form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.gym.workout.model.Client;
import com.gym.workout.model.GateOnline;
import com.gym.workout.model.Register;
import com.gym.workout.model.TypeUser;
import com.gym.workout.repository.RegisterRepository;

public class GateForm {

    @NotNull
    private Integer registerClient;

    public Integer getRegisterClient() {
        return registerClient;
    }

}
