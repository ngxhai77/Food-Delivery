package com.example.deleverysystem.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data


@Table(name = "users")
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fullname")
    private String displayName ;

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

    @OneToMany(mappedBy = "userInfo",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Orders> orders ;


    public UserInfo(){

    }


    public UserInfo( String fullname , String email , String phone , String address){
        this.displayName = fullname ;
        this.email = email ;
        this.phone = phone ;
        this.address = address ;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                ", fullname='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ward=" + ward +
                '}';
    }
}
