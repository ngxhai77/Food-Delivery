package com.example.deleverysystem.dto;

import com.example.deleverysystem.entity.MenuItems;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartResponse{
    private MenuItems menuItem;
    private Integer quantity;
    private BigDecimal price;
    // getters and setters
}
