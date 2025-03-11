package com.hackathon.smallparkingpassdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserAuthConfiguration {

    @Bean
    UserDetailsService userDetailsService() {
        // Create a dummy user for testing
        UserDetails dummyUser = User.builder()
                .username("Mat@Hackathon")
                .password(passwordEncoder().encode("password"))
                .roles("PL_PARKINGCARD_USER")
                .build();

        // Create an in-memory user details manager and add the dummy user
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(dummyUser);
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
