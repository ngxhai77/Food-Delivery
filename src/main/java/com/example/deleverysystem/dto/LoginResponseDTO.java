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
    private Integer id ;
    private String username ;
    private String password;
    private Set<Role> authorities;


    public LoginResponseDTO(String jwt, Integer id, String username, String password, Set<Role> authorities) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.jwt = jwt;
    }


}
