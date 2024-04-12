package com.example.deleverysystem.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data

@Table(name = "users")
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fullname")
    private String fullname ;

    @Column(name = "email")
    private String email ;

    @Column(name = "phone")
    private String phone ;

    @Column(name = "address")
    private String address ;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private ApplicationUser userAccount ;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward ;

//    @OneToMany(mappedBy = "users",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private List<Order> orders ;



    public UserInfo(){
        super();
    }


    public UserInfo( String fullname , String email , String phone , String address){
        super();
        this.fullname = fullname ;
        this.email = email ;
        this.phone = phone ;
        this.address = address ;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ward=" + ward +
                '}';
    }
}
