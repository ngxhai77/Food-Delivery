package com.example.deleverysystem.dto;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountDTO {

    private String fullName ;
    private String userName ;
    private String password ;
    private String email ;
    private String phone ;
    private String address ;
    private String role ;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

//    private List<UserAccountDTO> userInfoList;
//
//    // existing getters and setters...
//
//    public List<UserAccountDTO> getUserInfoList() {
//        return userInfoList;
//    }
//
//    public void setUserInfoList(List<UserAccountDTO> userInfoList) {
//        this.userInfoList = userInfoList;
//    }
}
