package com.project.bankproj.config;

import com.project.bankproj.exeption.UnauthorizedException;
import com.project.bankproj.validation.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity()
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity.httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeHttpRequests(authorizeHttpRequests ->
                            authorizeHttpRequests
                                    .requestMatchers(
                                            "/auth/login",
                                            "/auth/new-token",
                                            "/auth/refresh",
                                            "/swagger-ui.html",
                                            "/swagger-ui/**",
                                            "/v3/api-docs/**"
                                    )
                                    .permitAll()
                                    .anyRequest().authenticated()
                                    .and()
                                    .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    )
                    .build();
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}