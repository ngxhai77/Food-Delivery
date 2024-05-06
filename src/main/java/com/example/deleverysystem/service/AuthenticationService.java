package com.example.deleverysystem.service;


import com.example.deleverysystem.dto.LoginResponseDTO;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.BlacklistedToken;
import com.example.deleverysystem.entity.Role;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.repository.RoleRepository;
import com.example.deleverysystem.repository.TokenBlacklistRepository;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    public ApplicationUser registerUser(String displayName ,String username, String password){


        String endcodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        // Create a new UserInfo object and set the fullname
        UserInfo userInfo = new UserInfo();
        userInfo.setDisplayName(displayName);

        // Create a new ApplicationUser and set the UserInfo
        ApplicationUser user = new ApplicationUser(username, endcodedPassword, authorities);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setUserInfo(userInfo);

        // Set the user to the UserInfo
        userInfo.setUserAccount(user);


        return userRepository.save(user);



    }



    public ResponseEntity<LoginResponseDTO>  loginUser(String username, String password){

        try {
            Authentication auth  = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            String token  = tokenService.generateJwt(auth);
            ApplicationUser user = userRepository.findByUsername(username).get();
            Set<Role> roles = user.getAuthorities().stream()
                    .map(authority -> new Role(authority.getAuthority()))
                    .collect(Collectors.toSet());
            return  ResponseEntity.ok(new LoginResponseDTO(token));

        }catch(AuthenticationException e){
            return ResponseEntity.status(401).build();
        }




    }

    public void logout(String token) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationTime(Instant.now().plusSeconds(360)); // replace jwtExpirationTime with your token's lifetime
        tokenBlacklistRepository.save(blacklistedToken);
    }


    public void changePassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));


        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New password and confirmation password do not match");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        // save the updated user in the database
    }

}
