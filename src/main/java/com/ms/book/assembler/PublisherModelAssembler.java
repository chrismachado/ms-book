package com.ms.book.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.controller.AddressRestController;
import com.ms.book.controller.PublisherRestController;
import com.ms.book.model.PublisherModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PublisherModelAssembler implements RepresentationModelAssembler<PublisherModel, EntityModel<PublisherModel>> {

    //TODO: add all published books
    @Override
    public EntityModel<PublisherModel> toModel(PublisherModel publisherModel) {
        return EntityModel.of(publisherModel,
                linkTo(methodOn(PublisherRestController.class).one(publisherModel.getId())).withSelfRel(),
                linkTo(methodOn(AddressRestController.class).one(publisherModel.getAddressModel().getId())).withRel("address"),
                linkTo(methodOn(PublisherRestController.class).all()).withRel("publishers"));
    }
}
