package com.gym.workout.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_tb")
public class UserGymWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{nome é obrigatório.}")
    private String username;

    @NotEmpty(message = "{senha é obrigatório.}")
    private String password;

    private TypeUser typeUser;

    public UserGymWorkout() {
    }

    public UserGymWorkout(String username, String password, TypeUser typeUser) {
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserGymWorkout that = (UserGymWorkout) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Administrator{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
