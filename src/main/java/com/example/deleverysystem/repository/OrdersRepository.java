package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}