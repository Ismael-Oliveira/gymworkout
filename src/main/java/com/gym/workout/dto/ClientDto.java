package com.gym.workout.dto;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Card;
import com.gym.workout.model.Client;
import com.gym.workout.model.Performance;
import com.gym.workout.model.SpreadSheet;
import com.gym.workout.model.TypeUser;

public class ClientDto {

    private Long id;
    private String name;
    private String email;
    private String birthDate;
    private TypeUser typeUser;
    private String password;
    private Instant dateCreated;
    private Card card;
    private List<Performance> performances = new ArrayList<>();
    private List<SpreadSheet> spreadSheet = new ArrayList<>();

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.birthDate = client.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.typeUser = client.getTypeUser();
        this.password = client.getPassword();
        this.card = client.getCard();
        this.dateCreated = client.getDateCreate();
        this.performances.addAll(client.getPerformances());
//        this.spreadSheet.addAll(client.getSpreadSheet());
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

    public String getPassword() {
        return password;
    }

    public Card getCard() {
        return card;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public List<SpreadSheet> getSpreadSheet() {
        return spreadSheet;
    }

    public static Page<ClientDto> converter(Page<Client> clients) {
        return clients.map(client -> new ClientDto(client));
    }
}
