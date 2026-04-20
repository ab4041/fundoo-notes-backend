package com.fundoonotes.fundoo_notes_backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long expirationTime;


    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }


    public String generateToken(Long userId) {

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + expirationTime)
                )
                .signWith(getSigningKey())
                .compact();

    }


    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


    public Long extractUserId(String token) {

        return Long.parseLong(
                extractClaims(token).getSubject()
        );

    }


    public boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());

    }


    public boolean validateToken(String token) {

        return !isTokenExpired(token);

    }
}