package com.example.deleverysystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false)
    private Integer id;



    @Column(name = "Name")
    private String restaurantName;

    @Column(name = "address")
    private String restaurantAddress;

    @Column(name = "phone")
    private String restaurantPhone;


    @Column(name = "cuisineType")
    private String cuisineType;


    //add delivery time if possible


   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward ;


    //add one to many relationship with orders
//    @OneToMany(mappedBy = "restaurants",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private List<Orders> orders ;


    public Restaurant(){
        super();
    }

    public Restaurant(String restaurantName, String restaurantAddress, String restaurantPhone, String cuisineType, Ward ward){
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhone = restaurantPhone;
        this.cuisineType = cuisineType;
         this.ward = ward;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
