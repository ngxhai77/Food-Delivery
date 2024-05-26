package com.example.deleverysystem.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Integer itemId;
    private Integer quantity;

    // getters and setters
}