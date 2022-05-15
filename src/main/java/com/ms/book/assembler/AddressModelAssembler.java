package com.ms.book.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ms.book.controller.AddressController;
import com.ms.book.model.AddressModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AddressModelAssembler implements RepresentationModelAssembler<AddressModel, EntityModel<AddressModel>> {
    @Override
    public EntityModel<AddressModel> toModel(AddressModel addressModel) {
        return EntityModel.of(addressModel,
                linkTo(methodOn(AddressController.class).one(addressModel.getId())).withSelfRel(),
                linkTo(methodOn(AddressController.class).all()).withRel("addresses"));
    }
}
