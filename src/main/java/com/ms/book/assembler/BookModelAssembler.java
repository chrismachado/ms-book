package com.ms.book.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.controller.AuthorRestController;
import com.ms.book.controller.BookRestController;
import com.ms.book.controller.PublisherRestController;
import com.ms.book.model.BookModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<BookModel, EntityModel<BookModel>> {
    @Override
    public EntityModel<BookModel> toModel(BookModel bookModel) {
        return EntityModel.of(bookModel,
                linkTo(methodOn(BookRestController.class).one(bookModel.getId())).withSelfRel(),
                linkTo(methodOn(AuthorRestController.class).one(bookModel.getAuthorModel().getId())).withRel("author"),
                linkTo(methodOn(PublisherRestController.class).one(bookModel.getPublisher().getId())).withRel("publisher"),
                linkTo(methodOn(BookRestController.class).all()).withRel("books"));
    }

    public EntityModel<BookModel> toModelAll(BookModel bookModel) {
        return EntityModel.of(bookModel,
                linkTo(methodOn(BookRestController.class).one(bookModel.getId())).withSelfRel(),
                linkTo(methodOn(BookRestController.class).all()).withRel("books"));
    }
}
