package com.ms.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_AUTHOR")
public class AuthorModel implements Serializable {
    public static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        try {
            String[] parts = fullName.split(" ");
            firstName = parts[0];
            lastName = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
