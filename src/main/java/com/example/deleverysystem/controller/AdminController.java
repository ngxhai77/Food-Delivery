package com.example.deleverysystem.controller;

import com.example.deleverysystem.dto.UserAccountDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService userInfoService;

    @Autowired
    public UserAccountDTO userAccountDTO;

    @GetMapping("/")
    public String testing(){
        return "ADMIN LEVEL ACCESS";
    }


    // PUT THIS TO THE USER ACCOUNT MAPPER
    private UserAccountDTO mapToUserAccountDTO(UserInfo userInfo) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setFullName(userInfo.getFullname());
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
    @GetMapping("/all")
    public List<UserAccountDTO> findAllUser() {
        List<UserInfo> userInfoList = userInfoService.findAll();
        return userInfoList.stream()
                .map(this::mapToUserAccountDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/find/{id}")
    public UserInfo findUserById(@PathVariable("id") Integer id){
        return userInfoService.findById(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setFullname(userInfoDTO.getFullname());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPhone(userInfoDTO.getPhone());
        userInfo.setAddress(userInfoDTO.getAddress());


         userInfoService.create(userInfo);
        return "User Created Successfully .Generated ID is : "+userInfo.getUserId();
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @RequestBody UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setFullname(userInfoDTO.getFullname());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPhone(userInfoDTO.getPhone());
        userInfo.setAddress(userInfoDTO.getAddress());

        userInfoService.update(id,userInfo);
        return "User Updated Successfully .Generated ID is : "+userInfo.getUserId();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userInfoService.deleteById(id);
        return "User Deleted Successfully .";
    }



}



