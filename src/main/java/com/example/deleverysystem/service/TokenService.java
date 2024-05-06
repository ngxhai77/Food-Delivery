package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.ApplicationUser;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

    public Integer getIdFromToken(HttpServletRequest request) throws HttpClientErrorException , Exception {
        // Extract the token from the Authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring(7); // remove "Bearer " prefix

        // Parse the token to get the claims
        JWTClaimsSet claims = JWTParser.parse(token).getJWTClaimsSet();

        // Extract the id from the claims
        return claims.getIntegerClaim("id");
    }


}
