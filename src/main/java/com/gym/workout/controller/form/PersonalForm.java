package com.gym.workout.controller.form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.gym.workout.model.Personal;
import com.gym.workout.model.TypeUser;

public class PersonalForm {

    @NotNull
    private String name;
    @NotNull @Email
    private String email;
    private Date birthDate;
    @NotNull
    private TypeUser typeUser;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public Personal converter() {
        return new Personal(this.getName(), this.getEmail(), convertToLocalDate(), this.getTypeUser(), "12345");
    }

    private LocalDate convertToLocalDate() {
        return this.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
