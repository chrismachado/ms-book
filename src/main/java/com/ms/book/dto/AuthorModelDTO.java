package com.ms.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class AuthorModelDTO {

    @NotBlank
    private String fullName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
}
