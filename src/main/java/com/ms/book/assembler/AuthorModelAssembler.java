package com.ms.book.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.controller.AuthorController;
import com.ms.book.controller.BookController;
import com.ms.book.model.AuthorModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class AuthorModelAssembler implements RepresentationModelAssembler<AuthorModel, EntityModel<AuthorModel>> {

    @Override
    public EntityModel<AuthorModel> toModel(AuthorModel authorModel) {
        return EntityModel.of(authorModel,
                linkTo(methodOn(AuthorController.class).one(authorModel.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).allBooks(authorModel.getId())).withRel("all_books"),
                linkTo(methodOn(AuthorController.class).all()).withRel("authors"));
    }

}
