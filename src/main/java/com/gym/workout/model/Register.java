package com.gym.workout.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Register {

    @Id
    private Integer id = 1;
    private Integer register;

    public Integer getId() {
        return id;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }
}
