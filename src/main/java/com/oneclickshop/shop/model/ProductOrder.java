package com.oneclickshop.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private Product product;

    private int quantity;
    private LocalDateTime purchaseDate;
}

