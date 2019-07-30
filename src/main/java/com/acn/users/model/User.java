package com.acn.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    private String phone;

    private String email;

    public User() {
    }

    public User(Address address) {
        this.address = address;
    }

    public User(Long id, String firstname, String lastname, String phone, Address address, String email) {
        this.id         = id;
        this.firstname  = firstname;
        this.lastname   = lastname;
        this.phone      = phone;
        this.address    = address;
        this.email      = email;
    }

    public void setMutableFields(String firstname, String lastname, Address address, String phone, String email) {
        this.firstname  = firstname;
        this.lastname   = lastname;
        this.phone      = phone;
        this.address    = address;
        this.email      = email;
    }
}
