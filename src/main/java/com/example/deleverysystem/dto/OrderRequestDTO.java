package com.example.deleverysystem.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequestDTO {

    private Integer userId;
    private Integer restaurantId;
    private Integer deliveryPersonId;
    private String deliveryAddress;
    private List<OrderItemDTO> orderItems;


}
