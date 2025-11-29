package com.recruiter.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // To enable method-level security like @PreAuthorize
public class SecurityConfig {

        // Removed final JwtAuthFilter jwtAuthFilter;
        // Removed final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                // WARNING: This line is for testing purposes only and disables ALL
                                                // security.
                                                // DO NOT USE IN PRODUCTION!
                                                .anyRequest().permitAll());
                // The following lines are commented out or removed to completely disable
                // security filters for testing.
                // DO NOT USE IN PRODUCTION!
                // .sessionManagement(session ->
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .authenticationProvider(authenticationProvider)
                // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}