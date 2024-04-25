package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;


    public String generateJwt(Authentication auth){
        Instant now = Instant.now();
        ApplicationUser user = (ApplicationUser) auth.getPrincipal();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .claim("id", user.getId()) // add id to the token
                .claim("username", user.getUsername()) // add username to the token
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
        try {
            // Decode and validate the token
            Jwt jwt = jwtDecoder.decode(token);

            // If the token is successfully decoded, it is valid
            return true;
        } catch (JwtException ex) {
            // If any exception occurs while decoding, the token is invalid
            return false;
        }

    }
}
