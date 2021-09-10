package com.gym.workout.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gym.workout.service.exceptions.DatabaseException;
import com.gym.workout.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException resourceNotFoundException,
            HttpServletRequest httpServletRequest) {

        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException databaseException,
            HttpServletRequest httpServletRequest) {

        String error = "Error database";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, databaseException.getMessage(),
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
