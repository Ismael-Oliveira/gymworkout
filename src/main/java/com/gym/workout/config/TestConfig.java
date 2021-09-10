package com.gym.workout.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gym.workout.model.Client;
import com.gym.workout.model.GateOnline;
import com.gym.workout.model.Register;
import com.gym.workout.model.TypeUser;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.GateRepository;
import com.gym.workout.repository.RegisterRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    GateRepository gateRepository;

    @Override
    public void run(String... args) throws Exception {

        GateOnline gateOnline = new GateOnline();

        Register register = new Register();
        register.setRegister(1000);
        registerRepository.save(register);

        Client client = new Client( 1000, "Maria", "mary@gec", LocalDate.of(2021, 05,
                                                                                18), TypeUser.CLIENT, "12345");

        clientRepository.save(client);
        gateRepository.save(gateOnline);
    }
}
