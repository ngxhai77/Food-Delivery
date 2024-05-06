package com.example.deleverysystem.controller;


import com.example.deleverysystem.common.ResponseObject;
import com.example.deleverysystem.dto.UserAccountDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.mapper.UserAccountMapper;
import com.example.deleverysystem.mapper.UserInfoMapper;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import com.example.deleverysystem.service.TokenService;
import com.example.deleverysystem.service.UserInfoService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
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

    @Autowired
    private TokenService tokenService;

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

            // Retrieve the UserInfo from the   database

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



    @PutMapping("/update")
    public ResponseEntity<String> updateUser(HttpServletRequest request, @RequestBody UserInfoDTO userInfoDTO) {

        return userInfoService.update(request, userInfoMapper.mapToUserInfo(userInfoDTO));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        return  userInfoService.deleteU(request);

    }
}
