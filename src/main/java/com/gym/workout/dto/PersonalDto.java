package com.gym.workout.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Personal;
import com.gym.workout.model.TypeUser;

public class PersonalDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private TypeUser typeUser;

    public PersonalDto(Personal personal) {
        this.id = personal.getId();
        this.name = personal.getName();
        this.email = personal.getEmail();
        this.birthDate = personal.getBirthDate();
        this.typeUser = personal.getTypeUser();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public static Page<PersonalDto> converter(Page<Personal> personals) {
        return personals.map(PersonalDto::new);
    }
}
