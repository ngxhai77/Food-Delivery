package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}