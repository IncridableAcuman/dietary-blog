package com.diet.backend.util;

import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access_time}")
    private Long accessTime;
    @Value("${jwt.refresh_time}")
    private Long refreshTime;
    @Getter
    private Key signingKey;

    @PostConstruct
    public void init(){
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user,Long expiryTime){
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .claim("id",user.getId())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("email",user.getEmail())
                .claim("role",user.getRole())
                .claim("avatar",user.getAvatar())
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime))
                .setIssuedAt(new Date())
                .signWith(signingKey)
                .compact();
    }
    public Map<String,String> getTokens(User user){
        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken",generateToken(user,accessTime));
        tokens.put("refreshToken",generateToken(user,refreshTime));
        return tokens;
    }
    public Claims extractClaim(String token) throws BadRequestException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
        catch (JwtException e){
            throw new BadRequestException("Invalid token: " + e.getMessage());
        }
    }
    public String extractSubject(String token)  {
        return extractClaim(token).getSubject();
    }
    public Date extractExpiryDate(String token) throws BadRequestException {
        return extractClaim(token).getExpiration();
    }
    public boolean validateToken(String token,String username) {
        try {
            final String extractUsername = extractSubject(token);
            if (extractExpiryDate(token).after(new Date())){
                return false;
            }
            return extractUsername.equals(username);
        } catch (RuntimeException e) {
            throw new BadRequestException("Invalid token");
        }
    }
}
