package com.example.deleverysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "paymenttransactions")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @Column(name = "paymentDate")
    private Date paymentDay;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "pay_type_id")
    private PaymentType paymentType;

    // Getters and setters
}