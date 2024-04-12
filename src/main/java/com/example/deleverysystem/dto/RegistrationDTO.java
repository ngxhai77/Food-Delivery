package com.example.deleverysystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class RegistrationDTO {

    private String userName ;
    private String password ;


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

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
