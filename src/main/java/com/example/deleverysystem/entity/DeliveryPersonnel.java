package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "deliverypersonnel")
public class DeliveryPersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliveryperson_id")
    private Integer deliveryPersonnelId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "vehicleType")
    private String vehicleType;

    // Getters and setters
}