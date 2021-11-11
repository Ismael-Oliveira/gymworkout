package com.gym.workout.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.workout.controller.form.UpdateWorkoutForm;
import com.gym.workout.controller.form.WorkoutForm;
import com.gym.workout.dto.WorkoutDto;
import com.gym.workout.service.WorkoutService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Api rest workouts")
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    @PostMapping("/workouts")
    @ApiOperation(value="Salva um exercicio de treino")
    public ResponseEntity<WorkoutDto> insert(@RequestBody @Valid WorkoutForm workoutForm, UriComponentsBuilder uriBuilder) {

        WorkoutDto workoutDto = workoutService.create(workoutForm);
        URI uri = uriBuilder.path("workouts/{id}").buildAndExpand(workoutDto.getId()).toUri();
        return ResponseEntity.created(uri).body(workoutDto);
    }

    @GetMapping("/workouts")
    @ApiOperation(value="Retorna uma lista de exercícios")
    public ResponseEntity<Page<WorkoutDto>> list(@PageableDefault(sort = "nameExercise", direction = Sort.Direction.ASC, page = 0,
            size = 1000) Pageable page) {

        Page<WorkoutDto> workoutDtos = workoutService.findAll(page);
        return ResponseEntity.ok().body(workoutDtos);
    }

    @GetMapping(value = "/workouts/{id}")
    @ApiOperation(value="Retorna um exercício de treino")
    public ResponseEntity<WorkoutDto> findById(@PathVariable Long id) {
        WorkoutDto workoutDto = workoutService.findById(id);
        if(workoutDto != null) {
            return ResponseEntity.ok().body(workoutDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/workouts/{id}")
    @ApiOperation(value="Atualiza um exercício de treino")
    public ResponseEntity<WorkoutDto> update(@PathVariable Long id, @RequestBody UpdateWorkoutForm updateWorkoutForm) {

        WorkoutDto workoutDto = workoutService.update(id, updateWorkoutForm);
        if(workoutDto != null) {
            return ResponseEntity.ok().body(workoutDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/workouts/{id}")
    @ApiOperation(value="Deleta um exercício de treino")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
