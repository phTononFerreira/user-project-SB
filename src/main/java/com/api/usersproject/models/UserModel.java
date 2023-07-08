package com.api.usersproject.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class UserModel implements Serializable {
    private static final long serialVersionID = 1L;

    // ATRIBUTO ID (COM GERAÇÃO AUTOMÁTICA USANDO O UUID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // OUTROS ATRIBUTOS / COLUNAS NO DB
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 50)
    private String contact;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
