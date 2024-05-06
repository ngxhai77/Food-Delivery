package com.example.deleverysystem.mapper;


import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    public UserInfoDTO mapToUserInfoDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setDisplayName(userInfo.getDisplayName());
        userInfoDTO.setEmail(userInfo.getEmail());
        userInfoDTO.setPhone(userInfo.getPhone());
        userInfoDTO.setAddress(userInfo.getAddress());
        return userInfoDTO;
    }

    public UserInfo mapToUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setDisplayName(userInfoDTO.getDisplayName());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPhone(userInfoDTO.getPhone());
        userInfo.setAddress(userInfoDTO.getAddress());
        return userInfo;
    }

}
