package com.diet.backend.util;

import com.diet.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

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
    public String generateAccessToken(User user){
        return generateToken(user,accessTime);
    }
    public String refreshToken(User user){
        return generateToken(user,refreshTime);
    }
    public Claims extractClaim(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractSubject(String token){
        return extractClaim(token).getSubject();
    }
    public Date extractExpiryDate(String token){
        return extractClaim(token).getExpiration();
    }
    public boolean validateToken(String token,String username) throws BadRequestException {
        try {
            final String extractUsername = extractSubject(token);
            return (extractUsername.equals(username)) && extractExpiryDate(token).after(new Date());
        } catch (RuntimeException e) {
            throw new BadRequestException("Invalid token");
        }
    }
}
