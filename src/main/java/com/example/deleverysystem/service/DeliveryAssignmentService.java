package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.DeliveryAssignment;
import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.entity.Orders;
import com.example.deleverysystem.entity.Status;
import com.example.deleverysystem.repository.DeliveryAssignmentRepository;
import com.example.deleverysystem.repository.OrdersRepository;
import com.example.deleverysystem.repository.StatusRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryAssignmentService {

    private final DeliveryAssignmentService deliveryAssignmentService;
    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;


    public DeliveryAssignmentService(DeliveryAssignmentService deliveryAssignmentService) {
        this.deliveryAssignmentService = deliveryAssignmentService;
    }
    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public void confirmDelivery(Integer orderId , HttpServletRequest request) throws Exception {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Status status = statusRepository.findByStatus("Delivered");
        if (status == null) {
            throw new Exception("Status  not found in the database.");
        }
        order.setStatus(status); // Correct usage


        Integer id = deliveryPersonnelService.getDeliveryPersonIdFromToken(request);
        order.setDeliveryPersonnel(deliveryPersonnelService.findById(id));
        orderRepository.save(order);

//        DeliveryAssignment deliveryAssignment = new DeliveryAssignment() ;
//        deliveryAssignment.setOrder(order);
//        deliveryAssignment.setDeliveryPersonnel(deliveryPersonnelService.findById(id));
//        deliveryAssignment.setStatus(status);
//        deliveryAssignment.setAssignedDateTime(LocalDateTime.now());
//        deliveryAssignmentRepository.save(deliveryAssignment);
//

    }




    public void updateDeliveryStatus(Integer orderId, String status) throws Exception {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Status statuss = statusRepository.findByStatus(status);
        if (status == null) {
            throw new Exception("Status  not found in the database.");
        }
        order.setStatus(statuss); // Correct usage
        orderRepository.save(order);

    }

    public List<Orders> findAllDeliveredOrders() {
        return orderRepository.findAllByDeliveryStatus("Delivered");
    }

    public List<Orders> findAllOrders() {
        return orderRepository.findAll();
    }


    public List<Orders> findDeliveryHistory(Integer id) {
        return orderRepository.findAllByDeliveryPersonnel_DeliveryPersonnelId(id);
    }

}
