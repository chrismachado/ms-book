package com.ms.book.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {
    public static final long serialVersionID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDRESS_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    private String country;
    private String state;
    private String city;
    private String streetName;
    private int addressNumber;
    private String zipcode;

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s, %d, %s",
                country, state, city, streetName, addressNumber, zipcode);
    }

    public void setFullAddress(String fullAddress) {
        try {
            String[] parts = fullAddress.split(",");
            this.country = parts[0].trim();
            this.state = parts[1].trim();
            this.city = parts[2].trim();
            this.streetName = parts[3].trim();
            this.addressNumber = Integer.parseInt(parts[4].trim());
            this.zipcode = parts[5].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

}
