package com.example.authenticate.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private final long accessTokenValidity = 1000 * 60 * 15;
    private final long refreshTokenValidity = 1000*60*60*24*7;

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenValidity);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenValidity);
    }

//    public String generatedAccessToken(string username){
//        return  generatedTo(username);
//    }
    private String generateToken(String username, long validity){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean isTokenValid (String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException e){
            return false;
        }
    }
}
