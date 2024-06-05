package com.example.deleverysystem.controller;


import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.entity.Orders;
import com.example.deleverysystem.service.DeliveryAssignmentService;
import com.example.deleverysystem.service.DeliveryPersonnelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin("*")
public class DeliveryController {

    //Quản lý các thao tác liên quan đến việc giao hàng như xác nhận giao hàng, cập nhật trạng thái giao hàng.
    //Hỗ trợ cho việc xem danh sách các đơn hàng đã được giao thành công.

    @Autowired
    private DeliveryAssignmentService deliveryAssignmentService;


    //Xác nhận giao hàng
    @PostMapping("/confirm")
    public ResponseEntity confirmDelivery(@RequestBody Integer orderId, HttpServletRequest request) throws Exception {
        deliveryAssignmentService.confirmDelivery(orderId, request);
        return ResponseEntity.ok("Delivery confirmed");
    }
    //Cập nhật trạng thái giao hàng

    @PostMapping("/update")
    public ResponseEntity updateDeliveryStatus(@RequestBody Integer orderId, String status) throws Exception {
        deliveryAssignmentService.updateDeliveryStatus(orderId, status);
        return ResponseEntity.ok("Delivery status updated");
    }

    //Xem danh sách các đơn hàng đã được giao thành công
    @GetMapping("/view")
    public ResponseEntity<List<Orders>> viewDeliveredOrders(){
        List<Orders> orders =  deliveryAssignmentService.findAllDeliveredOrders();
        return ResponseEntity.ok(orders);
    }

    //Xem lịch sử giao hàng của một nhân viên giao hàng
    @GetMapping("/view/{id}")
    public ResponseEntity<List<Orders>> viewDeliveryHistory(@PathVariable Integer id){
        List<Orders> orders =  deliveryAssignmentService.findDeliveryHistory(id);
        return ResponseEntity.ok(orders);
    }






}
