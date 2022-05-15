package com.ms.book.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.controller.AuthorController;
import com.ms.book.controller.BookController;
import com.ms.book.controller.PublisherController;
import com.ms.book.model.BookModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<BookModel, EntityModel<BookModel>> {
    @Override
    public EntityModel<BookModel> toModel(BookModel bookModel) {
        return EntityModel.of(bookModel,
                linkTo(methodOn(BookController.class).one(bookModel.getId())).withSelfRel(),
                linkTo(methodOn(AuthorController.class).one(bookModel.getAuthorModel().getId())).withRel("author"),
                linkTo(methodOn(PublisherController.class).one(bookModel.getPublisher().getId())).withRel("publisher"),
                linkTo(methodOn(BookController.class).all()).withRel("books"));
    }

    public EntityModel<BookModel> toModelAll(BookModel bookModel) {
        return EntityModel.of(bookModel,
                linkTo(methodOn(BookController.class).one(bookModel.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).all()).withRel("books"));
    }
}
