package com.example.deleverysystem.controller;

import com.example.deleverysystem.dto.OrderRequestDTO;
import com.example.deleverysystem.dto.OrderResponseDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.Orders;
import com.example.deleverysystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //Xử lý các yêu cầu liên quan đến đơn hàng như tạo, cập nhật, xóa đơn hàng.
    //Hỗ trợ cho việc xem danh sách đơn hàng, chi tiết đơn hàng.

    //Xem danh sách đơn hàng
    //Xem chi tiết đơn hàng
    //Tạo đơn hàng
    //Cập nhật đơn hàng
    //Xóa đơn hàng
    //Xem lịch sử đơn hàng


    @GetMapping("/vieworder")
    public List<OrderResponseDTO> viewOrder() {
        return orderService.findAll();
    }


    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(HttpServletRequest request ,@RequestBody OrderRequestDTO orderRequest) throws Exception {
        orderService.createOrder(request,orderRequest);
        return ResponseEntity.ok("Order created successfully");
    }

    @GetMapping("/orderHistory")
    public List<Orders> orderHistory(HttpServletRequest request) throws Exception {
        return orderService.findAllByUserid(request);
    }


    @PostMapping("/from-cart")
    public ResponseEntity<?> createOrderFromCart(HttpServletRequest request) throws Exception {
        Pair<UserInfoDTO, OrderResponseDTO> order = orderService.createOrderFromCart(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("/cancelorder")
    public String cancelOrder() {
        return "Order cancelled successfully!";
    }


    @GetMapping("/vieworderdetails")
    public String viewOrderDetails() {
        return "Order details viewed successfully!";
    }


    @GetMapping("/updateorderstatus")
    public String updateOrderStatus() {
        return "Order status updated successfully!";
    }


}
