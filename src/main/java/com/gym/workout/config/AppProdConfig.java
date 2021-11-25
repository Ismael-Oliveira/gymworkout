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
@Profile("prod")
public class AppProdConfig implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    GateRepository gateRepository;

    @Override
    public void run(String... args) throws Exception {

        GateOnline gateOnline = new GateOnline();
        gateOnline.setQuantityClient(0);
        gateOnline.setSerial(0);
        gateRepository.save(gateOnline);

        Register register = new Register();
        register.setRegister(999);
        registerRepository.save(register);

        UserGymWorkout admin = new UserGymWorkout("admin@gmail.com", "admin", TypeUser.ADMINISTRATOR);
        UserGymWorkout user = new UserGymWorkout("user@gmail.com", "user", TypeUser.CLIENT);

        userRepository.save(admin);
        userRepository.save(user);

        Performance pf = new Performance(1.64, 64.0, 37, 1, "");
        performanceRepository.save(pf);

        Category cat1 = new Category("Braços");
        Workout workout = new Workout("supino", 3, 3, 3, cat1);

        Category cat2 = new Category("Pernas");
        Workout workout2 = new Workout("Legs", 3, 3, 3, cat2);

        Set<Category> categories = new HashSet<>();
        categories.addAll(Arrays.asList(cat1, cat2));

        Set<Category> categories2 = new HashSet<>();
        categories2.addAll(Arrays.asList(cat2));

        categoryRepository.save(cat1);
        workoutRepository.save(workout);

        categoryRepository.save(cat2);
        workoutRepository.save(workout2);

        Card card = new Card(categories);
//        card.getClient().add(client);
        cardRepository.save(card);

        Card card2 = new Card(categories2);
//        card2.getClient().add(client2);
        cardRepository.save(card2);


        Client client = new Client( 1000, "John snow", "mary@gmail.com", LocalDate.of(1984, 05,
                18), TypeUser.CLIENT, "12345", card);

        Client client2 = new Client( 1001, "Taka Hino", "mar@gec", LocalDate.of(2000, 05,
                18), TypeUser.CLIENT, "12345", new Card());

        client2.setCard(card2);
        client2.getPerformances().add(pf);

        clientRepository.save(client);
        clientRepository.save(client2);
    }
}
