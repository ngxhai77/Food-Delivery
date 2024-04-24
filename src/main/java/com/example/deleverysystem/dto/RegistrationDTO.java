package com.example.deleverysystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RegistrationDTO {

    private String displayName;
    private String userName ;
    private String password ;

    private String confirmPassword;


    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "fullName='" + displayName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
