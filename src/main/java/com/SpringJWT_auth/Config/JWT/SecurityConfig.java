package com.SpringJWT_auth.Config.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    // this is authorisation related - so we used filter chain for that

    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private JwtAuthEntryPoint point;


    @Bean // don't miss this annotation @Bean otherwise - you are configurations is not able to run

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // CONFIGURATION : configuration with the help of customizers

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/product/**")
//                        .hasRole("ADMIN")
                        .authenticated()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                ;

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
