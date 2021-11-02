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

import com.gym.workout.controller.form.EquipmentForm;
import com.gym.workout.controller.form.UpdateEquipmentForm;
import com.gym.workout.dto.EquipmentDto;
import com.gym.workout.service.EquipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Api rest clientes")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping("/equipments")
    @ApiOperation(value="Salva um equipamneto")
    public ResponseEntity<EquipmentDto> insert(@RequestBody @Valid EquipmentForm equipmentForm,
            UriComponentsBuilder uriBuilder) {

        EquipmentDto equipmentDto = equipmentService.create(equipmentForm);
        URI uri = uriBuilder.path("equipments/{id}").buildAndExpand(equipmentDto.getId()).toUri();
        return ResponseEntity.created(uri).body(equipmentDto);
    }

    @GetMapping("/equipments")
    @ApiOperation(value="Retorna uma lista de equipamentos")
    public ResponseEntity<Page<EquipmentDto>> list(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0,
            size = 10) Pageable page) {

        Page<EquipmentDto> equipmentDtos = equipmentService.findAll(page);
        return ResponseEntity.ok().body(equipmentDtos);
    }

    @GetMapping(value = "/equipments/{id}")
    @ApiOperation(value="Retorna um equipamento")
    public ResponseEntity<EquipmentDto> findById(@PathVariable Long id) {
        EquipmentDto equipmentDto = equipmentService.findById(id);
        if(equipmentDto != null) {
            return ResponseEntity.ok().body(equipmentDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/equipments/{id}")
    public ResponseEntity<EquipmentDto> update(@PathVariable Long id, @RequestBody UpdateEquipmentForm updateEquipmentForm) {

        EquipmentDto equipmentDto = equipmentService.update(id, updateEquipmentForm);
        if(equipmentDto != null) {
            return ResponseEntity.ok().body(equipmentDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/equipments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
