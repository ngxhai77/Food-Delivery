package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.OrderItem;
import com.example.deleverysystem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class OrderItemService {

    public final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findAll(){
        return orderItemRepository.findAll();
    }

    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public OrderItem findById(Integer id){
        return orderItemRepository.findById(id).orElse(null);
    }
    public OrderItem createOrderItems(OrderItem orderItem){
        return orderItemRepository.save(orderItem);

    }


}
