package com.diet.backend.util;

import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
        if (secret.getBytes(StandardCharsets.UTF_8).length < 32){
            throw new IllegalArgumentException("JWT secret must be at least 32 bytes!");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessToken(User user){
        return  generateToken(user,accessTime);
    }
    public String generateRefreshToken(User user){
        return  generateToken(user,refreshTime);
    }

    public String generateToken(User user,Long expiryTime){
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .claim("id",user.getId())
                .claim("role",user.getRole())
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime))
                .setIssuedAt(new Date())
                .signWith(signingKey)
                .compact();
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
            return extractExpiryDate(token).after(new Date()) && extractUsername.equals(username);
        } catch (RuntimeException e) {
            throw new BadRequestException("Invalid token");
        }
    }
}
