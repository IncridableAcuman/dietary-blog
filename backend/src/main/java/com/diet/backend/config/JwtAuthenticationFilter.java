package com.diet.backend.config;

import com.diet.backend.exception.UnAuthorizeException;
import com.diet.backend.service.UserDetailsService;
import com.diet.backend.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService service;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        String jwt=null;
        String username=null;
        if (header != null && header.startsWith("Bearer ")){
            jwt=header.substring(7);
            try {
                username=jwtUtil.extractSubject(jwt);
            } catch (ExpiredJwtException e){
                throw new UnAuthorizeException("Token expired");
            } catch (JwtException e){
                throw new UnAuthorizeException("Invalid token");
            }
        }
        if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails=this.service.loadUserByUsername(username);
            try {
                if (jwtUtil.validateToken(jwt,username)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    throw new UnAuthorizeException("Token is not valid");
                }
            } catch (RuntimeException e) {
                throw new UnAuthorizeException("User unauthorized");
            }

        }
        filterChain.doFilter(request,response);
    }
}
