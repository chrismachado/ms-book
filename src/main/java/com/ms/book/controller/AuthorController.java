package com.ms.book.controller;

import com.ms.book.assembler.AuthorModelAssembler;
import com.ms.book.dto.AuthorModelDTO;
import com.ms.book.model.AuthorModel;
import com.ms.book.model.BookModel;
import com.ms.book.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class AuthorController {

    @Autowired
    AuthorService service;

    @PostMapping("/author/save")
    public ResponseEntity<EntityModel<AuthorModel>> save(@RequestBody @Valid AuthorModelDTO authorModelDTO){
        AuthorModel authorModel = new AuthorModel();
        BeanUtils.copyProperties(authorModelDTO, authorModel);
        return new ResponseEntity<>(service.save(authorModel), HttpStatus.CREATED);
    }

    @GetMapping("author/{uuid}")
    public ResponseEntity<EntityModel<AuthorModel>> one(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.one(uuid), HttpStatus.OK);
    }

    @GetMapping("/author/all")
    public CollectionModel<EntityModel<AuthorModel>> all() {
        return service.all();
    }

    @PutMapping("author/{uuid}")
    public ResponseEntity<EntityModel<AuthorModel>> update(@RequestBody @Valid AuthorModelDTO authorModelDto, @PathVariable UUID uuid) {
        AuthorModel authorModel = new AuthorModel();
        BeanUtils.copyProperties(authorModelDto, authorModel);
        return new ResponseEntity<>(service.update(authorModel, uuid), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("author/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.delete(uuid), HttpStatus.OK);
    }


}
