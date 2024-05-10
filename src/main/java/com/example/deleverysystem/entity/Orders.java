package com.example.deleverysystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId ;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo ;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryperson_id" )
    private DeliveryPersonnel deliveryPersonnel;



    @Column(name = "orderDateTime")
    private LocalDateTime OrderDateTime ;


    @Column(name = "totalAmount")
    private DecimalFormat totalAmount ;

    @Column(name = "deliveryAddress")
    private String deliveryAddress ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;



}
