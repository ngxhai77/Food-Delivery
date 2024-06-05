package com.example.deleverysystem.service;


import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.repository.DeliveryPersonnelRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private TokenService tokenService;

    public List<DeliveryPersonnel> findAll(){
        return deliveryPersonnelRepository.findAll();
    }

    public DeliveryPersonnel save(DeliveryPersonnel deliveryPersonnel){
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    public DeliveryPersonnel findById(Integer id){
        return deliveryPersonnelRepository.findById(id).orElse(null);
    }


    public DeliveryPersonnel createDeliveryPersonnel(HttpServletRequest request, DeliveryPersonnel deliveryPersonnel) throws Exception {
        Integer id = tokenService.getIdFromToken(request );
        deliveryPersonnel.setUserAccountId(id);
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }


    public void deleteById(Integer id){
        deliveryPersonnelRepository.deleteById(id);
    }


    public Integer getDeliveryPersonIdFromToken(HttpServletRequest request) throws Exception {
        // Get the id from the token
        Integer id = tokenService.getIdFromToken(request);

        // Find the DeliveryPersonnel object using the id
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepository.findById(id).orElse(null);

        // If the DeliveryPersonnel object is not found, throw an exception
        if (deliveryPersonnel == null) {
            throw new Exception("DeliveryPersonnel not found");
        }

        // Return the deliveryperson_id
        return deliveryPersonnel.getDeliveryPersonnelId();
    }

}
