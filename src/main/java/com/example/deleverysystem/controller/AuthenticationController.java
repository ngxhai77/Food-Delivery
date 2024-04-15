package com.example.deleverysystem.controller;


import com.example.deleverysystem.dto.LoginRequestDTO;
import com.example.deleverysystem.dto.LoginResponseDTO;
import com.example.deleverysystem.dto.RegistrationDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService ;


    // FIX DTO
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        ApplicationUser applicationUser =  authenticationService.registerUser(body.getFullName(),body.getUserName(),body.getPassword());
       return applicationUser;
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO body){
        return authenticationService.loginUser(body.getUserName(),body.getPassword());

    }
}
