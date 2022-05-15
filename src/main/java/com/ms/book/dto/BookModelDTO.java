package com.ms.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class BookModelDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String ISBN;

    private UUID publisherId;
    private UUID authorId;

    private int year;
    private int pages;
}
