package com.oneclickshop.shop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Table(name = "users")
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role; // SELLER, BUYER, ADMIN


}