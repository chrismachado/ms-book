package com.ms.book.controller;

import com.ms.book.dto.BookModelDTO;
import com.ms.book.model.AuthorModel;
import com.ms.book.model.BookModel;
import com.ms.book.model.PublisherModel;
import com.ms.book.service.AuthorService;
import com.ms.book.service.BookService;
import com.ms.book.service.PublisherService;
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
public class BookRestController {

    @Autowired
    BookService service;

    @Autowired
    AuthorService authorService;

    @Autowired
    PublisherService publisherService;

    @PostMapping("book/save")
    public ResponseEntity<EntityModel<BookModel>> save(@RequestBody @Valid BookModelDTO bookModelDTO) {
        BookModel book = new BookModel();
        BeanUtils.copyProperties(bookModelDTO, book);
        AuthorModel author = authorService.one(bookModelDTO.getAuthorId()).getContent();
        PublisherModel publisher = publisherService.one(bookModelDTO.getPublisherId()).getContent();

        book.setAuthorModel(author);
        book.setPublisher(publisher);

        return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
    }

    @GetMapping("book/{uuid}")
    public ResponseEntity<EntityModel<BookModel>> one(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.one(uuid), HttpStatus.OK);
    }

    @GetMapping("book/all")
    public CollectionModel<EntityModel<BookModel>> all() {
        return service.all();
    }

    @PutMapping("book/{uuid}")
    public ResponseEntity<EntityModel<BookModel>> update(@RequestBody @Valid BookModelDTO bookModelDTO, @PathVariable UUID uuid) {
        BookModel book = new BookModel();
        BeanUtils.copyProperties(bookModelDTO, book);
        AuthorModel author = authorService.one(bookModelDTO.getAuthorId()).getContent();
        PublisherModel publisher = publisherService.one(bookModelDTO.getPublisherId()).getContent();

        book.setAuthorModel(author);
        book.setPublisher(publisher);

        return new ResponseEntity<>(service.update(book, uuid), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("book/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.delete(uuid), HttpStatus.OK);
    }

    @GetMapping("books/author/{uuid}")
    public CollectionModel<EntityModel<BookModel>> allBooks(@PathVariable UUID uuid) {
        return service.all(uuid);
    }
}
