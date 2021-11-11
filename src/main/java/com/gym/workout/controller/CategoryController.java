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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gym.workout.controller.form.CategoryForm;
import com.gym.workout.dto.CategoryDto;
import com.gym.workout.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Api rest clientes")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    @ApiOperation(value="Salva uma categoria")
    public ResponseEntity<CategoryDto> insert(@RequestBody @Valid CategoryForm categoryForm, UriComponentsBuilder uriBuilder) {

        CategoryDto categoryDto = categoryService.create(categoryForm);
        URI uri = uriBuilder.path("categories/{id}").buildAndExpand(categoryDto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDto);
    }

    @GetMapping("/categories")
    @ApiOperation(value="Retorna uma lista de categorias de exercícios")
    public ResponseEntity<Page<CategoryDto>> list(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0,
            size = 1000) Pageable page) {

        Page<CategoryDto> categoryDtos = categoryService.findAll(page);
        return ResponseEntity.ok().body(categoryDtos);
    }

    @GetMapping(value = "/categories/{id}")
    @ApiOperation(value="Retorna uma categoria de exercícios")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        if(categoryDto != null) {
            return ResponseEntity.ok().body(categoryDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/categories{id}")
    @ApiOperation(value="Deleta uma categoria")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
