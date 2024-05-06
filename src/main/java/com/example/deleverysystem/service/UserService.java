package com.example.deleverysystem.service;

import com.example.deleverysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class   UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder ;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the userdetail service");
       return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user is not valid"));
    }


}
