package com.ms.book.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.assembler.AddressModelAssembler;
import com.ms.book.controller.AddressRestController;
import com.ms.book.exception.AddressNotFoundException;
import com.ms.book.model.AddressModel;
import com.ms.book.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    AddressModelAssembler assembler;

    public EntityModel<AddressModel> save(AddressModel addressModel) {
        return assembler.toModel(repository.save(addressModel));
    }

    public EntityModel<AddressModel> one(UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .orElseThrow(() -> new AddressNotFoundException(uuid)));
    }

    public CollectionModel<EntityModel<AddressModel>> all() {
        List<EntityModel<AddressModel>> addresses = repository
                .findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(addresses,
                linkTo(methodOn(AddressRestController.class).all()).withRel("addresses"));
    }

    public EntityModel<AddressModel> update(AddressModel newAddress, UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .map(addressModel -> {
                    BeanUtils.copyProperties(newAddress, addressModel);
                    addressModel.setId(uuid);
                    return repository.save(addressModel);
                })
                .orElseGet(() -> {
                    newAddress.setId(uuid);
                    return repository.save(newAddress);
                }));
    }

    public String delete(UUID uuid) {
        try {
            repository.deleteById(uuid);
        } catch (EmptyResultDataAccessException e) {
            throw new AddressNotFoundException(uuid);
        }
        return String.format("Address[%s] deleted successfully!", uuid);
    }


}
