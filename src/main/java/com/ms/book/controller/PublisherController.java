package com.ms.book.controller;

import com.ms.book.dto.PublisherModelDTO;
import com.ms.book.model.AddressModel;
import com.ms.book.model.PublisherModel;
import com.ms.book.service.AddressService;
import com.ms.book.service.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class PublisherController {

    @Autowired
    PublisherService service;

    @Autowired
    AddressService addressService;

    @PostMapping("publisher/save")
    public ResponseEntity<EntityModel<PublisherModel>> save(@RequestBody @Valid PublisherModelDTO publisherModelDto) {
        PublisherModel publisherModel = new PublisherModel();
        AddressModel addressModel = addressService.one(publisherModelDto.getAddressId()).getContent();
        publisherModel.setName(publisherModelDto.getName());
        publisherModel.setAddressModel(addressModel);

        return new ResponseEntity<>(service.save(publisherModel), HttpStatus.CREATED);
    }

    @GetMapping("publisher/all")
    public CollectionModel<EntityModel<PublisherModel>> all() {
        return service.all();
    }

    @GetMapping("publisher/{uuid}")
    public ResponseEntity<EntityModel<PublisherModel>> one(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.one(uuid), HttpStatus.OK);
    }

    @PutMapping("publisher/{uuid}")
    public ResponseEntity<EntityModel<PublisherModel>> update(@RequestBody @Valid PublisherModelDTO publisherModelDto, @PathVariable UUID uuid) {
        PublisherModel publisherModel = new PublisherModel();
        AddressModel addressModel = addressService.one(publisherModelDto.getAddressId()).getContent();
        publisherModel.setName(publisherModelDto.getName());
        publisherModel.setAddressModel(addressModel);

        return new ResponseEntity<>(service.update(publisherModel, uuid), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("publisher/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.delete(uuid), HttpStatus.OK);
    }
}
