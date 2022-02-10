package com.gym.workout.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gym.workout.model.Card;
import com.gym.workout.model.Category;
import com.gym.workout.model.Client;
import com.gym.workout.model.GateOnline;
import com.gym.workout.model.Performance;
import com.gym.workout.model.Register;
import com.gym.workout.model.TypeUser;
import com.gym.workout.model.UserGymWorkout;
import com.gym.workout.model.Workout;
import com.gym.workout.repository.CardRepository;
import com.gym.workout.repository.CategoryRepository;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.GateRepository;
import com.gym.workout.repository.PerformanceRepository;
import com.gym.workout.repository.RegisterRepository;
import com.gym.workout.repository.UserRepository;
import com.gym.workout.repository.WorkoutRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    UserRepository userRepository;

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

        Performance pf0 = new Performance(1.70, 75.0, 39, 0, "Uma mulher de coragem");
        performanceRepository.save(pf0);

        Performance pf = new Performance(1.64, 64.0, 37, 1, "John da montanha");
        performanceRepository.save(pf);

        Performance pf2 = new Performance(1.64, 63.0, 38, 1, "perdeu 1 kilo");
        performanceRepository.save(pf2);

        Category cat1 = new Category("Braços");
        Workout workout = new Workout("supino", 3, 3, 3, cat1);
        Workout workout1 = new Workout("supino1", 3, 3, 3, cat1);
        Workout workout2 = new Workout("supino2", 3, 3, 3, cat1);
        Workout workout3 = new Workout("supino3", 3, 3, 3, cat1);

        Category cat2 = new Category("Pernas");
        Workout workout4 = new Workout("Legs1", 3, 3, 3, cat2);
        Workout workout5 = new Workout("Legs2", 3, 3, 3, cat2);
        Workout workout6 = new Workout("Legs3", 3, 3, 3, cat2);
        Workout workout7 = new Workout("Legs4", 3, 3, 3, cat2);

        Set<Category> categories = new HashSet<>();
        categories.addAll(Arrays.asList(cat1, cat2));

        Set<Category> categories2 = new HashSet<>();
        categories2.addAll(Arrays.asList(cat2));

        categoryRepository.save(cat1);
        workoutRepository.save(workout);
        workoutRepository.save(workout1);
        workoutRepository.save(workout2);
        workoutRepository.save(workout3);

        categoryRepository.save(cat2);
        workoutRepository.save(workout4);
        workoutRepository.save(workout5);
        workoutRepository.save(workout6);
        workoutRepository.save(workout7);

        Card card = new Card(categories);
//        card.getClient().add(client);
        cardRepository.save(card);

        Card card2 = new Card(categories2);
//        card2.getClient().add(client2);
        cardRepository.save(card2);


        Client client = new Client( 1000, "Maria", "mary@gec", LocalDate.of(2021, 05,
                18), TypeUser.CLIENT, "12345", card);

        Client client2 = new Client( 1001, "John brown", "mar@gec", LocalDate.of(2021, 05,
                18), TypeUser.CLIENT, "12345", new Card());

        Client client3 = new Client( 1002, "Tanaka Hino", "tak@gec", LocalDate.of(2020, 05,
                18), TypeUser.CLIENT, "54321", card);

        clientRepository.save(client);
        UserGymWorkout user = new UserGymWorkout(client.getEmail(), client.getPassword(), client.getTypeUser(), client.getId());
        userRepository.save(user);

        clientRepository.save(client3);

        client.getPerformances().add(pf0);

        client2.setCard(card2);
        client2.getPerformances().add(pf);
        clientRepository.save(client2);

        client2.getPerformances().add(pf2);
        clientRepository.save(client2);

        gateRepository.save(gateOnline);
    }
}
