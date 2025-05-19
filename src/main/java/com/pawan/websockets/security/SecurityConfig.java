package com.pawan.websockets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/contact", "/register", "/login", "/logout", "/home").permitAll()
                        //.requestMatchers("/chatBox","/chat-lobby/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/ws/**").permitAll()  // Allow WebSocket handshake without authentication
                        .anyRequest().permitAll()
                )
                .csrf(csrf->csrf.ignoringRequestMatchers("/ws/**","/chat-lobby/**","chatBox/**"))
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                )
                .logout(config -> config
                        .logoutSuccessUrl("/"));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}