package com.example.deleverysystem.mapper;

import com.example.deleverysystem.dto.UserAccountDTO;
import com.example.deleverysystem.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserAccountMapper {

    public UserAccountDTO mapToUserAccountDTO(UserInfo userInfo) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setDisplayName(userInfo.getDisplayName());
        userAccountDTO.setEmail(userInfo.getEmail());
        userAccountDTO.setPhone(userInfo.getPhone());
        userAccountDTO.setAddress(userInfo.getAddress());
        // Convert the collection of authorities into a single string
        String role = userInfo.getUserAccount().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        userAccountDTO.setRole(role);

        return userAccountDTO;
    }
}
