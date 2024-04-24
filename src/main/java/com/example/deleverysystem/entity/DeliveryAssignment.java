package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "deliveryassignments")
public class DeliveryAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "deliveryperson_id")
    private DeliveryPersonnel deliveryPersonnel;


    @Column(name = "assignedDateTime")
    private Date assignedDateTime;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    // Getters and setters
}