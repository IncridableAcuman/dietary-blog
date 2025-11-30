package com.diet.backend.util;

import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    @Bean
    public void addCookie(String refreshToken){
        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setValue(refreshToken);
        cookie.setPath("/");
        cookie.setMaxAge(604800);
    }

    @Bean
    public void clearCookie(){
        Cookie cookie = new Cookie("refreshToken",null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setValue(null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
    }
}
