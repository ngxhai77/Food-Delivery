package com.example.deleverysystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {


    private String displayName ;

    private String email ;

    private String phone ;

    private String address ;





}
