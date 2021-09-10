package com.gym.workout.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GateOnline {

    @Id
    private Integer id;
    private Instant dateArrive;
    private Integer quantityClient;
    private Integer serial;

    public GateOnline() {
        this.id = 1;
        this.dateArrive = Instant.now();
        this.quantityClient = 0;
    }

    public Instant getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Instant dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Integer getQuantityClient() {
        return quantityClient;
    }

    public void setQuantityClient(Integer quantityClient) {
        this.quantityClient = quantityClient;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }
}
