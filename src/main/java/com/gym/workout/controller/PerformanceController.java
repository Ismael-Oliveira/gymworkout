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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.workout.controller.form.PerformanceForm;
import com.gym.workout.dto.PerformanceDto;
import com.gym.workout.service.PerformanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Api rest clientes")
public class PerformanceController {

    @Autowired
    PerformanceService performanceService;

    @PostMapping("/performances")
    @ApiOperation(value="Salvar avaliação física")
    public ResponseEntity<PerformanceDto> insert(@RequestBody @Valid PerformanceForm performanceForm,
            UriComponentsBuilder uriBuilder) {

        PerformanceDto performanceDto = performanceService.create(performanceForm);
        URI uri = uriBuilder.path("performances/{id}").buildAndExpand(performanceDto.getId()).toUri();
        return ResponseEntity.created(uri).body(performanceDto);
    }

    @GetMapping("/performances")
    @ApiOperation(value="Retornar lista de avaliações físicas")
    public ResponseEntity<Page<PerformanceDto>> list(@PageableDefault(sort = "dateEvaluation", direction = Sort.Direction.ASC,
            page = 0,
            size = 10) Pageable page) {

        Page<PerformanceDto> performanceDtos = performanceService.findAll(page);
        return ResponseEntity.ok().body(performanceDtos);
    }

    @GetMapping(value = "/performances/{id}")
    @ApiOperation(value="Retornar avaliação física")
    public ResponseEntity<PerformanceDto> findById(@PathVariable Long id) {
        PerformanceDto performanceDto = performanceService.findById(id);
        if(performanceDto != null) {
            return ResponseEntity.ok().body(performanceDto);
        }
        return ResponseEntity.notFound().build();
    }

}
