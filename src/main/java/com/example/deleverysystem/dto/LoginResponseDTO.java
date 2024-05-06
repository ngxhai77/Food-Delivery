package com.example.deleverysystem.dto;

import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class LoginResponseDTO {

    @Setter
    @Getter
    private String jwt ;



    public LoginResponseDTO(String jwt) {


        this.jwt = jwt;
    }



}
