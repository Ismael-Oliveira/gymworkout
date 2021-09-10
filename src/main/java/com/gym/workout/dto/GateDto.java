package com.gym.workout.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Client;
import com.gym.workout.model.GateOnline;
import com.gym.workout.model.TypeUser;

public class GateDto {

    private Instant dateArrive;
    private Integer quantityClient;
    private Integer serial;

    public GateDto(GateOnline gateOnline) {
        this.dateArrive = gateOnline.getDateArrive();
        this.quantityClient = gateOnline.getQuantityClient();
        this.serial = gateOnline.getSerial();
    }

    public Instant getDateArrive() {
        return dateArrive;
    }

    public Integer getQuantityClient() {
        return quantityClient;
    }

    public Integer getSerial() {
        return serial;
    }
}
