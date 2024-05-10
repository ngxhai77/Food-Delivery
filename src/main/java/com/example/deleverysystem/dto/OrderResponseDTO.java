package com.example.deleverysystem.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderResponseDTO {

    private Integer orderId;
    private Integer userId;
     private Integer restaurantId;
        private Integer deliveryPersonId;
        private String deliveryAddress;


}
