package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "paymenttype")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_type_id")
    private Integer paymentTypeId;


    @Column(name = "name")
    private String name;

    // Getters and setters


}

