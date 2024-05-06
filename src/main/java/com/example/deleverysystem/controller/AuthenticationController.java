package com.example.deleverysystem.controller;


import com.example.deleverysystem.dto.ChangePasswordDTO;
import com.example.deleverysystem.dto.LoginRequestDTO;
import com.example.deleverysystem.dto.LoginResponseDTO;
import com.example.deleverysystem.dto.RegistrationDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService ;

    // FIX DTO
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body){
        if (!body.getPassword().equals(body.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Error: Password and Confirm Password do not match!");
        }
        ApplicationUser applicationUser =  authenticationService.registerUser(body.getDisplayName(),body.getUserName(),body.getPassword());
        return ResponseEntity.ok().body("User registered successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequestDTO body){
        return authenticationService.loginUser(body.getUserName(),body.getPassword());

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        authenticationService.logout(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO body) {
        try {
            authenticationService.changePassword(body.getUserName(), body.getOldPassword(), body.getNewPassword(), body.getConfirmPassword());
            return ResponseEntity.ok().body("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
