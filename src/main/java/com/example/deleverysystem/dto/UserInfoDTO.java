package com.example.deleverysystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    @Column(name = "fullname")
    private String fullname ;

    @Column(name = "email")
    private String email ;

    @Column(name = "phone")
    private String phone ;

    @Column(name = "address")
    private String address ;





}
