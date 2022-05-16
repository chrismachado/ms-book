package com.ms.book.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.assembler.AuthorModelAssembler;
import com.ms.book.controller.AuthorRestController;
import com.ms.book.exception.AuthorNotFoundException;
import com.ms.book.model.AuthorModel;
import com.ms.book.repository.AuthorRepository;
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
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    @Autowired
    AuthorModelAssembler assembler;

    public EntityModel<AuthorModel> save(AuthorModel authorModel) {
        return assembler.toModel(repository.save(authorModel));
    }

    public CollectionModel<EntityModel<AuthorModel>> all() {
        List<EntityModel<AuthorModel>> authors = repository
                .findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(authors,
                linkTo(methodOn(AuthorRestController.class).all()).withRel("authors"));
    }

    public EntityModel<AuthorModel> one(UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .orElseThrow(() -> new AuthorNotFoundException(uuid)));

    }

    public String delete(UUID uuid) {
        try {
            repository.deleteById(uuid);
        } catch (EmptyResultDataAccessException ex) {
            throw new AuthorNotFoundException(uuid);
        }
        return String.format("Author[%s] deleted successfully!", uuid);
    }

    public EntityModel<AuthorModel> update(AuthorModel newAuthor, UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .map(authorModel -> {
                    BeanUtils.copyProperties(newAuthor, authorModel);
                    authorModel.setId(uuid);
                    return repository.save(authorModel);
                })
                .orElseGet(
                        () -> {
                            newAuthor.setId(uuid);
                            return repository.save(newAuthor);
                        }
                ));
    }

}
