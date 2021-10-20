package com.gym.workout.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.workout.controller.form.PersonalForm;
import com.gym.workout.controller.form.UpdatePersonalForm;
import com.gym.workout.dto.PersonalDto;
import com.gym.workout.service.PersonalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Api rest clientes")
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @PostMapping("/personals")
    @ApiOperation(value="Salva um instrutor")
    public ResponseEntity<PersonalDto> insert(@RequestBody @Valid PersonalForm personalForm, UriComponentsBuilder uriBuilder) {

        PersonalDto personalDto = personalService.create(personalForm);
        URI uri = uriBuilder.path("personals/{id}").buildAndExpand(personalDto.getId()).toUri();
        return ResponseEntity.created(uri).body(personalDto);
    }

    @GetMapping("/personals")
    @ApiOperation(value="Retorna uma lista de instrutores")
    public ResponseEntity<Page<PersonalDto>> list(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0,
            size = 10) Pageable page) {

        Page<PersonalDto> personalDtos = personalService.findAll(page);
        return ResponseEntity.ok().body(personalDtos);
    }

    @GetMapping(value = "/personals/{id}")
    @ApiOperation(value="Retorna um instrutor")
    public ResponseEntity<PersonalDto> findById(@PathVariable Long id) {
        PersonalDto personalDto = personalService.findById(id);
        if(personalDto != null) {
            return ResponseEntity.ok().body(personalDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/personals/{id}")
    @ApiOperation(value="Atualiza um instrutor")
    public ResponseEntity<PersonalDto> update(@PathVariable Long id, @RequestBody UpdatePersonalForm updatePersonalForm) {

        PersonalDto personalDto = personalService.update(id, updatePersonalForm);
        if(personalDto != null) {
            return ResponseEntity.ok().body(personalDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/personals/{id}")
    @ApiOperation(value="Deleta um instrutor")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
