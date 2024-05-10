package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findAllByUserInfo (Integer id);
}