package com.example.deleverysystem.service;


import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.repository.DeliveryPersonnelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPersonnelService {

//Xử lý các yêu cầu liên quan đến việc quản lý nhân viên giao hàng như thêm, sửa, xóa thông tin nhân viên giao hàng.
    //Hỗ trợ cho việc xem lịch sử giao hàng của các đơn hàng.

    private final DeliveryPersonnelRepository deliveryPersonnelRepository;


    public DeliveryPersonnelService(DeliveryPersonnelRepository deliveryPersonnelRepository) {
        this.deliveryPersonnelRepository = deliveryPersonnelRepository;
    }


    public List<DeliveryPersonnel> findAll(){
        return deliveryPersonnelRepository.findAll();
    }

    public DeliveryPersonnel save(DeliveryPersonnel deliveryPersonnel){
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    public DeliveryPersonnel findById(Integer id){
        return deliveryPersonnelRepository.findById(id).orElse(null);
    }

    public DeliveryPersonnel createDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel){
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }




    public void deleteById(Integer id){
        deliveryPersonnelRepository.deleteById(id);
    }



}
