package com.niranjan.Jwt;


import com.niranjan.commonlib.Dto.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
