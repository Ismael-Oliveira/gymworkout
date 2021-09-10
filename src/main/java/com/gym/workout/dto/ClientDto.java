package com.gym.workout.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Client;
import com.gym.workout.model.TypeUser;

public class ClientDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private TypeUser typeUser;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.birthDate = client.getBirthDate();
        this.typeUser = client.getTypeUser();
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

    public static Page<ClientDto> converter(Page<Client> clients) {
        return clients.map(ClientDto::new);
    }
}
