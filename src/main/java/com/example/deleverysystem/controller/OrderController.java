package com.example.deleverysystem.controller;

import com.example.deleverysystem.dto.OrderRequestDTO;
import com.example.deleverysystem.entity.Orders;
import com.example.deleverysystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Orders> viewOrder() {
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



    @GetMapping("/cancelorder")
    public String cancelOrder() {
        return "Order cancelled successfully!";
    }

    @GetMapping("/vieworderhistory")
    public String viewOrderHistory() {
        return "Order history viewed successfully!";
    }

    @GetMapping("/vieworderdetails")
    public String viewOrderDetails() {
        return "Order details viewed successfully!";
    }


    @GetMapping("/updateorderstatus")
    public String updateOrderStatus() {
        return "Order status updated successfully!";
    }

    @GetMapping("/vieworderstatushistory")
    public String viewOrderStatusHistory() {
        return "Order status history viewed successfully!";
    }

}
