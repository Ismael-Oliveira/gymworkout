package com.gym.workout.controller.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.gym.workout.model.Card;
import com.gym.workout.model.Category;
import com.gym.workout.model.Client;
import com.gym.workout.model.SpreadSheet;
import com.gym.workout.model.Workout;
import com.gym.workout.repository.CardRepository;
import com.gym.workout.repository.ClientRepository;
import com.gym.workout.repository.SpreadSheetRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

public class UpdateSpreadSheetForm {

    private Card card;

    public Card getCard() {
        return card;
    }

    public Client update(Long id, ClientRepository clientRepository, CardRepository cardRepository, SpreadSheetRepository spreadSheetRepository) {

        try {
            Set<Category> categories = this.getCard().getCategories();

            List<SpreadSheet> spreadSheetList = new ArrayList<>();

//            categories.forEach( category -> {
//                List<Workout> workouts = category.getPlanning().stream().collect(Collectors.toList());
//                SpreadSheet spread = new SpreadSheet(workouts, category);
//                spreadSheetRepository.save(spread);
//                spreadSheetList.add(spread);
//            });

            Card card = new Card();
            card.setCategories(this.getCard().getCategories());
            cardRepository.save(card);

            Client client = clientRepository.getById(id);
//            client.setSpreadSheet(spreadSheetList);
            client.setCard(card);
            clientRepository.save(client);
            return client;

        } catch(EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
