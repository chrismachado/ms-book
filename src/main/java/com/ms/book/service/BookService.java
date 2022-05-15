package com.ms.book.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.assembler.BookModelAssembler;
import com.ms.book.controller.AuthorController;
import com.ms.book.controller.BookController;
import com.ms.book.exception.BookNotFoundException;
import com.ms.book.model.AuthorModel;
import com.ms.book.model.BookModel;
import com.ms.book.repository.BookRepository;
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
public class BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    BookModelAssembler assembler;

    public EntityModel<BookModel> save(BookModel bookModel) {
        return assembler.toModel(repository.save(bookModel));
    }

    public EntityModel<BookModel> one(UUID uuid) {
        return assembler.toModel(repository
                .findById(uuid)
                .orElseThrow(() -> new BookNotFoundException(uuid)));
    }

    public CollectionModel<EntityModel<BookModel>> all() {
        List<EntityModel<BookModel>> books = repository
                .findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(books,
                linkTo(methodOn(BookController.class).all()).withRel("books"));
    }

    public EntityModel<BookModel> update(BookModel newBookModel, UUID uuid) {
        return assembler.toModel( repository
                .findById(uuid)
                .map(bookModel -> {
                    BeanUtils.copyProperties(newBookModel, bookModel);
                    bookModel.setId(uuid);
                    return repository.save(bookModel);
                })
                .orElseGet(() -> {
                    newBookModel.setId(uuid);
                    return repository.save(newBookModel);
                }));
    }

    public String delete(UUID uuid) {
        try {
            repository.deleteById(uuid);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(uuid);
        }
        return String.format("Book[%s] deleted successfully!", uuid);
    }

    public CollectionModel<EntityModel<BookModel>> all(UUID uuid) {
        List<EntityModel<BookModel>> books = repository
                .findByAuthorId(uuid.toString().replace("-", ""))
                .stream()
                .map(assembler::toModelAll)
                .collect(Collectors.toList());

        return CollectionModel.of(books,
                linkTo(methodOn(AuthorController.class).one(uuid)).withRel("author"));
    }
}
