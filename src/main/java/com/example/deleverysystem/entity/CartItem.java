package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItems menuItem;

    private int quantity;
    private BigDecimal price;



    // Getters and setters
}