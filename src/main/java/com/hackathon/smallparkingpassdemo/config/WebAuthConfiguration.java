package com.hackathon.smallparkingpassdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebAuthConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);


        http.authorizeHttpRequests(r -> r
            .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/token/authenticate").hasAnyRole("PL_PARKINGCARD_USER")
            .requestMatchers(HttpMethod.GET, "/api/20250227/car").hasAnyRole("PL_PARKINGCARD_USER")
            .requestMatchers(HttpMethod.POST, "/api/20250227/car").hasAnyRole("PL_PARKINGCARD_USER")
            .requestMatchers(HttpMethod.PATCH, "/api/20250227/car/**").hasAnyRole("PL_PARKINGCARD_USER")
            .requestMatchers(HttpMethod.GET, "/api/20250227/pass").hasAnyRole("PL_PARKINGCARD_USER")
            .requestMatchers(HttpMethod.POST, "/api/20250227/pass/generate/**").hasAnyRole("PL_PARKINGCARD_USER")
            .anyRequest().denyAll());

        http.addFilterBefore(new HeaderFilter(), AuthorizationFilter.class);
        http.addFilterBefore(new ActuatorRoboFilter(), HeaderFilter.class);


        return http.build();
    }    
}
