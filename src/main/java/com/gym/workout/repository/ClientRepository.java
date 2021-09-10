package com.gym.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.workout.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client getByRegister(Integer registerClient);
}
