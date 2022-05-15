package com.ms.book.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PUBLISHER")
public class PublisherModel implements Serializable {
    public static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLISHER_ID", columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private AddressModel addressModel;

}
