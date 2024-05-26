package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Orders;
import com.example.deleverysystem.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
        @Query("SELECT o FROM Orders o WHERE o.userInfo.userId = :userId")
        List<Orders> findOrdersByUserId(@Param("userId") Integer userId);

}