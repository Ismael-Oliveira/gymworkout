package com.gym.workout.controller.form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.gym.workout.model.Card;
import com.gym.workout.model.Client;
import com.gym.workout.model.Register;
import com.gym.workout.model.TypeUser;
import com.gym.workout.repository.RegisterRepository;

public class ClientForm {

    @NotNull(message="Nome: Não pode ser nulo.")
    private String name;
    @NotNull(message="Email: Não pode ser nulo e deve possuir um email válido.")
    @Email
    private String email;
    @NotNull(message="Data de aniversário: Não pode ser nulo.")
    private Date birthDate;
    @NotNull
    private TypeUser typeUser;

    private Card card;

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

    public Card getCard() {
        return card;
    }

    public Client converter(RegisterRepository registerRepository) {
        Register register = registerRepository.findAll().get(0);
        Integer value = register.getRegister();
        value++;
        register.setRegister(value);
        registerRepository.save(register);
        return new Client(value, this.getName(), this.getEmail(), convertToLocalDate(), this.getTypeUser(),
                value.toString(), this.getCard());
    }

    private LocalDate convertToLocalDate() {
        return this.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
