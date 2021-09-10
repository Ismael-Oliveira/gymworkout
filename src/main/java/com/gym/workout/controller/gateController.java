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

import com.gym.workout.controller.form.ClientForm;
import com.gym.workout.controller.form.GateForm;
import com.gym.workout.controller.form.UpdateClientForm;
import com.gym.workout.dto.ClientDto;
import com.gym.workout.dto.GateDto;
import com.gym.workout.model.GateOnline;
import com.gym.workout.service.ClientService;
import com.gym.workout.service.GateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="Api rest gate online")
public class gateController {

    @Autowired
    ClientService clienteService;

    @Autowired
    GateService gateService;

    @PostMapping("/gate/in")
    @ApiOperation(value="Entrada aluno")
    public ResponseEntity<GateDto> arrive(@RequestBody @Valid GateForm gateForm, UriComponentsBuilder uriBuilder) {

        GateDto gateDto = null;
        ClientDto clientDto = clienteService.getByRegister(gateForm.getRegisterClient());
        if(clientDto != null) {
            gateDto = gateService.comeIn(gateForm.getRegisterClient());
            URI uri = uriBuilder.path("gate/in").buildAndExpand(gateDto.getQuantityClient()).toUri();
            return ResponseEntity.created(uri).body(gateDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/gate/out")
    @ApiOperation(value="Saida aluno")
    public ResponseEntity<GateDto> leave(@RequestBody @Valid GateForm gateForm, UriComponentsBuilder uriBuilder) {

        GateDto gateDto = null;
        ClientDto clientDto = clienteService.getByRegister(gateForm.getRegisterClient());
        if(clientDto != null) {
            gateDto = gateService.comeOut(gateForm.getRegisterClient());
            URI uri = uriBuilder.path("gate/out").buildAndExpand(gateDto.getQuantityClient()).toUri();
            return ResponseEntity.created(uri).body(gateDto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/gate")
    @ApiOperation(value="Retorna quantida de alunos")
    public ResponseEntity<GateDto> quantity() {

        GateOnline gateOnline = gateService.getGateOnline();
        return ResponseEntity.ok().body(new GateDto(gateOnline));
    }

}
