package com.example.deleverysystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "imageURL")
    private String imageURL;
    // Getters and setters


}
