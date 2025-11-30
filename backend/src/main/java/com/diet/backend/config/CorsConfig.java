package com.diet.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource configurationSource  (){
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("http://localhost:5173"));
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Accept",
                "Content-Type",
                "Origin",
                "X-Request-With",
                "Cookie"
        ));
        config.setExposedHeaders(List.of("Authorization","Set-Cookie"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
}
