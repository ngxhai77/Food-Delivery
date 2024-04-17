package com.example.deleverysystem.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDTO {

    private String userName ;
    private String password ;


    @Override
    public String toString() {
        return "RegistrationDTO{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
