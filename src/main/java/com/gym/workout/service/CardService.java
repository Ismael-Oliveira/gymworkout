package com.gym.workout.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.workout.controller.form.CardForm;
import com.gym.workout.dto.CardDto;
import com.gym.workout.model.Card;
import com.gym.workout.repository.CardRepository;
import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

//    public CardDto create(CardForm cardForm) {
//
//        Card card = cardForm.converter();
//        card = cardRepository.save(card);
//        return new CardDto(card);
//    }
//
//    public Page<CardDto> findAll(Pageable page) {
//
//        Page<Card> cards = cardRepository.findAll(page);
//
//        return CardDto.converter(cards);
//    }
//
//    public CardDto findById(Long id) {
//
//        try {
//            Card card = cardRepository.getById(id);
//            return new CardDto(card);
//
//        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    public void delete(Long id) {
//        try {
//            cardRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException(e.getMessage());
//        }
//    }

}
