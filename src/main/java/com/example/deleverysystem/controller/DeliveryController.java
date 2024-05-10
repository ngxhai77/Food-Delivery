package com.example.deleverysystem.controller;


import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.service.DeliveryPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@CrossOrigin("*")
public class DeliveryController {

    //Quản lý các thao tác liên quan đến việc giao hàng như xác nhận giao hàng, cập nhật trạng thái giao hàng.
    //Hỗ trợ cho việc xem danh sách các đơn hàng đã được giao thành công.

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    //Xác nhận giao hàng

    //Cập nhật trạng thái giao hàng

    //Xem danh sách các đơn hàng đã được giao thành công

    //Xem lịch sử giao hàng của một nhân viên giao hàng

    @PostMapping("/create")
    public DeliveryPersonnel createDeliveryPersonnel( @RequestBody DeliveryPersonnel deliveryPersonnel){
        return deliveryPersonnelService.createDeliveryPersonnel(deliveryPersonnel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(Integer id){
        deliveryPersonnelService.deleteById(id);
    }


    @GetMapping("/view/{id}")
    public DeliveryPersonnel findById(Integer id){
        return deliveryPersonnelService.findById(id);
    }


    @PostMapping("/save")
    public Iterable<DeliveryPersonnel> viewAllDeliveryPersonnel(){
        return deliveryPersonnelService.findAll();
    }






}
