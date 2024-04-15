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


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ApplicationUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(ApplicationUser userAccount) {
        this.userAccount = userAccount;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

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
