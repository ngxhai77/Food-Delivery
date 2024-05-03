package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menuitems")
public class MenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
