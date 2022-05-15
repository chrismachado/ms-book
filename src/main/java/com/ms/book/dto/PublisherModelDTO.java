package com.ms.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class PublisherModelDTO {

    @NotBlank
    private String name;
    private UUID addressId;
}
