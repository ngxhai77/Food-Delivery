package com.example.deleverysystem.mapper;


import com.example.deleverysystem.dto.OrderItemDTO;
import com.example.deleverysystem.dto.OrderResponseDTO;
import com.example.deleverysystem.entity.OrderItem;
import com.example.deleverysystem.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {

    public OrderResponseDTO convertToDTO(Orders order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(this::convertOrderItemToDTO)
                .collect(Collectors.toList());
        dto.setOrderItems(orderItemDTOs);

        return dto;
    }


    private OrderItemDTO convertOrderItemToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setItemId(orderItem.getItem().getItemId());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }


}
