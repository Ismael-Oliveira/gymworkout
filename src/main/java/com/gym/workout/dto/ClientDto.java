package com.gym.workout.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Card;
import com.gym.workout.model.Client;
import com.gym.workout.model.Performance;
import com.gym.workout.model.TypeUser;

public class ClientDto {

    private Long id;
    private String name;
    private String email;
    private String birthDate;
    private TypeUser typeUser;
    private Card card;
    private List<Performance> performances = new ArrayList<>();

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.birthDate = client.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.typeUser = client.getTypeUser();
        this.card = client.getCard();
        this.performances.addAll(client.getPerformances());
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

    public String getBirthDate() {
        return birthDate;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public Card getCard() {
        return card;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public static Page<ClientDto> converter(Page<Client> clients) {
        return clients.map(client -> new ClientDto(client));
    }
}
