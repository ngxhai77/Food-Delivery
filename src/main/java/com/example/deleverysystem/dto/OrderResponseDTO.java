package com.example.deleverysystem.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class OrderResponseDTO {

    private Integer orderId;
    private String deliveryAddress;
    private LocalDateTime orderDateTime;
    private BigDecimal totalAmount;
    private List<OrderItemDTO> orderItems;

}
