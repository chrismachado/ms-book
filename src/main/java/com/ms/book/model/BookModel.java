package com.ms.book.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_BOOK")
public class BookModel implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID", columnDefinition = "BINARY(16)")
    private UUID id;
    private String title;
    private String description;
    private String ISBN;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "PUBLISHER_ID")
    private PublisherModel publisher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
    private AuthorModel authorModel;

    private int year;
    private int pages;


}
