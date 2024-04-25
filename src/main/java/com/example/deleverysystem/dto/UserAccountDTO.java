package com.example.deleverysystem.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class UserAccountDTO {

    private String displayName;
    private String userName ;
    private String password ;
    private String email ;
    private String phone ;
    private String address ;
    private String role ;

    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "fullName='" + displayName + '\'' +
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
