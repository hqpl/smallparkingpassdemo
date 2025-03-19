package com.hackathon.smallparkingpassdemo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebAuthConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());
        http.cors(cors ->cors.configurationSource(corsConfigurationSource()));
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://192.168.0.3:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("X-HACKATHON", "Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
