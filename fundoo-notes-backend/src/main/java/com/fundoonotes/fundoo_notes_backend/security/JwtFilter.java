package com.fundoonotes.fundoo_notes_backend.security;

import com.fundoonotes.fundoo_notes_backend.util.JwtUtil;
import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Check header exists
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;

        }

        // Extract token
        String token = authHeader.substring(7);

        try {

            Claims claims = jwtUtil.extractClaims(token);

            String userId = claims.getSubject();

            // attach userId into request context
            request.setAttribute("userId", userId);

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }

        filterChain.doFilter(request, response);
    }
}