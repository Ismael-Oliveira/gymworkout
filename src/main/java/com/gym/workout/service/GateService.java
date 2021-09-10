package com.gym.workout.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gym.workout.dto.GateDto;
import com.gym.workout.model.GateOnline;
import com.gym.workout.repository.GateRepository;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@Service
public class GateService {

    @Autowired
    GateRepository gateRepository;

    List<Integer> serials = new ArrayList<>();

    public GateDto comeIn(Integer serial) {
        return this.controlArrive(1, serial);
    }

    public GateDto comeOut(Integer serial) {
        return this.controlArrive(0, serial);
    }

    public GateOnline getGateOnline() {

        try {
            GateOnline gateOnline = gateRepository.getById(1);

            return gateOnline;

        } catch (EmptyResultDataAccessException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException(null);
        }
    }

    private GateDto controlArrive(Integer control, Integer serial) {
        Integer oldQuantity;
        GateOnline gateOnline = this.getGateOnline();

        if (gateOnline.getQuantityClient() > 0) {
            if(serials.contains(serial) && control == 1) {
                throw new ResourceNotFoundException(serial);
            }

            if (!serials.contains(serial) && control == 0) {
                throw new ResourceNotFoundException(serial);
            }

            if (serials.contains(serial) && control == 0) {
                serials.remove(serial);
            } else {
                serials.add(serial);
            }

            oldQuantity = gateOnline.getQuantityClient();
            oldQuantity = (control == 1) ? ++oldQuantity : --oldQuantity;
            gateOnline.setQuantityClient(oldQuantity);
            gateOnline.setSerial(serial);
        } else {
            serials.add(serial);
            gateOnline.setQuantityClient(1);
            gateOnline.setSerial(serial);
        }

        gateOnline.setDateArrive(Instant.now());
        gateOnline = gateRepository.save(gateOnline);
        return new GateDto(gateOnline);
    }

}
