package com.gym.workout.controller;

import java.net.URI;
import java.util.List;

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

import com.gym.workout.controller.form.ClientForm;
import com.gym.workout.controller.form.UpdateClientForm;
import com.gym.workout.dto.ClientDto;
import com.gym.workout.model.Client;
import com.gym.workout.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="Api rest clientes")
public class clientController {

    @Autowired
    ClientService clienteService;

    @PostMapping("/clients")
    @ApiOperation(value="Salva um aluno")
    public ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder) {

        ClientDto clientDto = clienteService.create(clientForm);
        URI uri = uriBuilder.path("clients/{id}").buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @GetMapping("/clients")
    @ApiOperation(value="Retorna uma lista de alunos")
    public ResponseEntity<Page<ClientDto>> list(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0,
            size = 10) Pageable page) {

        Page<ClientDto> clientDtos = clienteService.findAll(page);
        return ResponseEntity.ok().body(clientDtos);
    }

    @GetMapping(value = "/clients/{id}")
    @ApiOperation(value="Retorna um aluno")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto clientDto = clienteService.findById(id);
        if(clientDto != null) {
            return ResponseEntity.ok().body(clientDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody UpdateClientForm updateClientForm) {

        ClientDto clientDto = clienteService.update(id, updateClientForm);
        if(clientDto != null) {
            return ResponseEntity.ok().body(clientDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
