package com.ms.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressModelDTO {
    @NotBlank
    private String country;
    @NotBlank
    private String state;
    @NotBlank
    private String city;
    @NotBlank
    private String streetName;

    private int addressNumber;

    @NotBlank
    private String zipcode;
}
