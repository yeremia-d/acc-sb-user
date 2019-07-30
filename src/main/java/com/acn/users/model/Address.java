package com.acn.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String province;
    private String postalcode;

    public void setMutableFields(String street, String city, String province, String postalcode) {
        this.street     = street;
        this.city       = city;
        this.province   = province;
        this.postalcode = postalcode;
    }

}
