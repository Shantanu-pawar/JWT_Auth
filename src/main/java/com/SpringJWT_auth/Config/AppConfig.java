package com.SpringJWT_auth.Config;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppConfig {

    // we've to define the bean of userDetails service to define the auth related stuff
    @Bean
    // this is authentication
    public UserDetailsService userDetailService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("dad")
                .password(encoder.encode("dad")) // here just encrypting the password
                .roles("ADMIN")
                .build();

        UserDetails superAdmin = User.withUsername("john")
                .password(encoder.encode("john"))
                .roles("OWNER")
                .build();

        return new InMemoryUserDetailsManager(admin, superAdmin);
    }

    // just created bean of authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder )throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // this is password encryption mechanism
        return new BCryptPasswordEncoder();
    }
}

