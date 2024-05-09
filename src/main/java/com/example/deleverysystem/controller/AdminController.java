package com.example.deleverysystem.controller;

import com.example.deleverysystem.dto.UserAccountDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.mapper.UserAccountMapper;
import com.example.deleverysystem.mapper.UserInfoMapper;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
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


    @Autowired
    public UserAccountMapper userAccountMapper;

    @Autowired
    public UserInfoMapper userInfoMapper;


    @GetMapping("/")
    public String testing(){
        return "ADMIN LEVEL ACCESS";
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserAccountDTO>> getAllUser() {
        List<UserInfo> userInfoList = userInfoService.findAll();
        return ResponseEntity.ok(userInfoList.stream().map(this::mapToUserAccountDTO).collect(Collectors.toList()));
    }

     private UserAccountDTO mapToUserAccountDTO(UserInfo userInfo) {
        return userAccountMapper.mapToUserAccountDTO(userInfo);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable("id") Integer id){
        UserInfo  user =  userInfoService.findById(id);
            return ResponseEntity.ok(user); // Trả về 200 OK và đối tượng user

    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setDisplayName(userInfoDTO.getDisplayName());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPhone(userInfoDTO.getPhone());

         userInfoService.create(userInfo);
        return "User Created Successfully .Generated ID is : "+userInfo .getUserId();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Integer id, @RequestBody UserInfoDTO userInfoDTO){
        userInfoService.update(id,userInfoMapper.mapToUserInfo(userInfoDTO));
        return ResponseEntity.ok("User Updated Successfully .");
    }

//    @DeleteMapping("/delete/{id}")    // DELETE METHOD
//    public String deleteUser(@PathVariable("id") Integer id){
//        userInfoService.deleteById(id);
//        return "User Deleted Successfully .";
//    }



}



