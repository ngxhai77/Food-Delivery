package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Integer> {
}