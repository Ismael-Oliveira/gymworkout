package com.gym.workout.model;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Client {



    private Long id;
    private Integer register;
    private String name;
    private String email;
    private String password;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant birthDate;

    public Client() {}

    public Client(Long id, Integer register, String name, String email, String password, Instant birthDate) {
        this.id = id;
        this.register = register;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" + "register=" + register + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
