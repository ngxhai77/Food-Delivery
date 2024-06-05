package com.example.deleverysystem.controller;


import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.service.DeliveryPersonnelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliverypersonnel")
@CrossOrigin("*")
public class DeliveryPersonnelController {

    //Quản lý các thao tác liên quan đến nhân viên giao hàng như thêm, sửa, xóa thông tin nhân viên.
    //Hỗ trợ cho việc xem danh sách nhân viên giao hàng

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;
    //Thêm nhân viên giao hàng
    @PostMapping("/create")
    public ResponseEntity <DeliveryPersonnel>createDeliveryPersonnel(HttpServletRequest request, @RequestBody DeliveryPersonnel deliveryPersonnel) throws Exception {
        deliveryPersonnelService.createDeliveryPersonnel(request,deliveryPersonnel);
        return ResponseEntity.ok(deliveryPersonnel);
    }


    //Xóa nhân viên giao hàng
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(Integer id){
        deliveryPersonnelService.deleteById(id);
        return ResponseEntity.ok("Deleted successfully!");
    }


    @GetMapping("/view/{id}")
    public  DeliveryPersonnel findById(Integer id){
        return deliveryPersonnelService.findById(id);
    }


    @PostMapping("/save")
    public Iterable<DeliveryPersonnel> viewAllDeliveryPersonnel(){
        return deliveryPersonnelService.findAll();
    }


    //Sửa thông tin nhân viên giao hàng



    //Xem danh sách nhân viên giao hàng

}
