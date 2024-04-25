package com.example.deleverysystem.controller;


import com.example.deleverysystem.dto.UserAccountDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.mapper.UserAccountMapper;
import com.example.deleverysystem.mapper.UserInfoMapper;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import com.example.deleverysystem.service.UserInfoService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/")
    public String testing(){
        return "USER ACCESS LEVEL ";
    }

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService userInfoService;

    @Autowired
    public UserAccountDTO userAccountDTO;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserAccountMapper userAccountMapper;
    @Autowired
    public UserInfoMapper userInfoMapper;
    private UserAccountDTO mapToUserAccountDTO(UserInfo userInfo) {
        return userAccountMapper.mapToUserAccountDTO(userInfo);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoDTO> getUserInfo(HttpServletRequest request) {
        try {
            // Extract the token from the Authorization header
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = authHeader.substring(7);
            // Parse the token to get the claims
            JWTClaimsSet claims = JWTParser.parse(token).getJWTClaimsSet();

            // Extract the id and username from the claims
            Integer id = claims.getIntegerClaim("id");
            String username = claims.getStringClaim("username");

            // Retrieve the UserInfo from the database

            ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Check if the username matches
            if (!username.equals(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            //need a DTO to return the user info to the client .

            UserInfoDTO userInfoDTO = userInfoMapper.mapToUserInfoDTO(user.getUserInfo());
            return ResponseEntity.ok(userInfoDTO);
        } catch (Exception e) {
        //    e.printStackTrace();    // Log the error
            return ResponseEntity.status(   HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/find/{id}")
    public UserInfo findUserById(@PathVariable("id") Integer id){
        return userInfoService.findById(id);
    }



    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @RequestBody UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setDisplayName(userInfoDTO.getDisplayName());
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
