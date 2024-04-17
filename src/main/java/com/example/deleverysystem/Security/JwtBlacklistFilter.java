package com.example.deleverysystem.Security;

import com.example.deleverysystem.repository.TokenBlacklistRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtBlacklistFilter extends GenericFilterBean {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    public JwtBlacklistFilter(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    @Override   
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");

        if (token != null && tokenBlacklistRepository.existsByToken(token)) {
              servletResponse.getWriter().write("Token is blacklisted");
            SecurityContextHolder.clearContext();

        }

            filterChain.doFilter(request, servletResponse);
    }
}
