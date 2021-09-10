package com.gym.workout.dto;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Card;
import com.gym.workout.model.Category;

public class CardDto {

    private Long id;
    private String nameExercise;

//    public CardDto(Card card) {
//        this.id = card.getId();
//        this.nameExercise = card.getNameExercise();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return nameExercise;
//    }
//
//    public static Page<CardDto> converter(Page<Card> cards) {
//        return cards.map(CardDto::new);
//    }
}
