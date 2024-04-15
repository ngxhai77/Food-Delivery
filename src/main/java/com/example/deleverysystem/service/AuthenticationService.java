package com.example.deleverysystem.service;


import com.example.deleverysystem.dto.LoginResponseDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.Role;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.repository.RoleRepository;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private TokenService tokenService ;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public ApplicationUser registerUser(String fullname ,String username, String password){
        String endcodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        // Create a new UserInfo object and set the fullname
        UserInfo userInfo = new UserInfo();
        userInfo.setFullname(fullname);

        // Create a new ApplicationUser and set the UserInfo
        ApplicationUser user = new ApplicationUser(username, endcodedPassword, authorities);
        user.setUserInfo(userInfo);

        // Set the user to the UserInfo
        userInfo.setUserAccount(user);




        return userRepository.save(user);



    }



    public LoginResponseDTO loginUser(String username, String password){

        try {
            Authentication auth  = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            String token  = tokenService.generateJwt(auth);
            return  new LoginResponseDTO(userRepository.findByUsername(username).get(),token);
        }catch(AuthenticationException e){
            return new LoginResponseDTO(null,"");
        }


    }

}
