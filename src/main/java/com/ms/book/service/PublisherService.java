package com.ms.book.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.assembler.PublisherModelAssembler;
import com.ms.book.controller.PublisherController;
import com.ms.book.exception.PublisherNotFoundException;
import com.ms.book.model.PublisherModel;
import com.ms.book.repository.PublisherRepository;
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
public class PublisherService {

    @Autowired
    PublisherRepository repository;

    @Autowired
    PublisherModelAssembler assembler;

    public EntityModel<PublisherModel> save(PublisherModel publisherModel) {
        return assembler.toModel(repository.save(publisherModel));
    }

    public CollectionModel<EntityModel<PublisherModel>> all() {
        List<EntityModel<PublisherModel>> publishers = repository
                .findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(publishers,
                linkTo(methodOn(PublisherController.class).all()).withRel("publishers"));
    }

    public EntityModel<PublisherModel> one(UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .orElseThrow(() -> new PublisherNotFoundException(uuid)));
    }

    public EntityModel<PublisherModel> update(PublisherModel newPublisher, UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .map(publisherModel -> {
                    BeanUtils.copyProperties(newPublisher, publisherModel);
                    publisherModel.setId(uuid);
                    return repository.save(publisherModel);
                })
                .orElseGet(() -> {
                    newPublisher.setId(uuid);
                    return repository.save(newPublisher);
                }));
    }

    public String delete(UUID uuid) {
        try {
            repository.deleteById(uuid);
        } catch (EmptyResultDataAccessException e) {
            throw new PublisherNotFoundException(uuid);
        }
        return String.format("Publisher[%s] deleted successfully!", uuid);
    }
}
