package com.ms.book.controller;

import com.ms.book.dto.AddressModelDTO;
import com.ms.book.model.AddressModel;
import com.ms.book.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class AddressController {

    @Autowired
    AddressService service;


    @PostMapping("address/save")
    public ResponseEntity<EntityModel<AddressModel>> save(@RequestBody @Valid AddressModelDTO addressModelDTO) {
        AddressModel addressModel = new AddressModel();
        BeanUtils.copyProperties(addressModelDTO, addressModel);
        return new ResponseEntity<>(service.save(addressModel), HttpStatus.CREATED);
    }

    @GetMapping("address/{uuid}")
    public ResponseEntity<EntityModel<AddressModel>> one(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.one(uuid), HttpStatus.OK);
    }

    @GetMapping("address/all")
    public CollectionModel<EntityModel<AddressModel>> all() {
        return service.all();
    }

    @PutMapping("address/{uuid}")
    public ResponseEntity<EntityModel<AddressModel>> update(@RequestBody @Valid AddressModelDTO addressModelDTO, @PathVariable UUID uuid) {
        AddressModel addressModel = new AddressModel();
        BeanUtils.copyProperties(addressModelDTO, addressModel);
        return new ResponseEntity<>(service.update(addressModel, uuid), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("address/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.delete(uuid), HttpStatus.OK);
    }

}
