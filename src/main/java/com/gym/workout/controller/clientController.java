package com.gym.workout.controller;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.workout.model.Client;

@RestController
@RequestMapping(value = "/clients")
public class clientController {

    @GetMapping
    public ResponseEntity<Client> findAll() {
        Client client = new Client(1l, 1000, "Maria", "mary@gec", "1234", Instant.parse("1980-04-09T00:00:00Z"));
        return ResponseEntity.ok().body(client);
    }
}
