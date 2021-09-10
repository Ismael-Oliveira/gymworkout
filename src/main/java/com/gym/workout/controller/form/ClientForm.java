package com.gym.workout.controller.form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gym.workout.model.Client;
import com.gym.workout.model.Register;
import com.gym.workout.model.TypeUser;
import com.gym.workout.repository.RegisterRepository;

public class ClientForm {

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

    public Client converter(RegisterRepository registerRepository) {
        Register register = registerRepository.findAll().get(0);
        Integer value = register.getRegister();
        value++;
        register.setRegister(value);
        registerRepository.save(register);
        return new Client(value, this.getName(), this.getEmail(), convertToLocalDate(), this.getTypeUser(), value.toString());
    }

    private LocalDate convertToLocalDate() {
        return this.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
