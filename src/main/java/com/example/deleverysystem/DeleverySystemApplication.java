package com.example.deleverysystem;

import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.Role;
import com.example.deleverysystem.repository.RoleRepository;
import com.example.deleverysystem.repository.UserRepository;
import org.springframework.aop.TargetSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = "com.example.deleverysystem.entity")
@ComponentScan({"com.example.deleverysystem.controller","com.example.deleverysystem.service" , "com.example.deleverysystem.*"})
@EnableJpaRepositories(basePackages = "com.example.deleverysystem.repository")
public class DeleverySystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeleverySystemApplication.class, args);
    }



    @Bean
    CommandLineRunner run(RoleRepository roleRepository , UserRepository userRepository , PasswordEncoder passwordEncoder){
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            com.example.deleverysystem.entity.Role adminRole =  roleRepository.save(new com.example.deleverysystem.entity.Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            ApplicationUser admin = new ApplicationUser(1,"admin", passwordEncoder.encode("password"),roles );
            userRepository.save(admin);
        };
    }

}
