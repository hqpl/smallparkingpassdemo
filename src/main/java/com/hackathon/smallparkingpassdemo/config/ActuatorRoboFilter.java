package com.hackathon.smallparkingpassdemo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ActuatorRoboFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Check if the request is for not Actuator endpoint
        if (!request.getRequestURI().startsWith("/actuator")) {
            // Allow other endpoints to proceed without header check
            filterChain.doFilter(request, response);
            return;
        }

        String xAuthenticatedHeader = request.getHeader("X-ACTUATOR-ROBOT");

        // check for correct http header
        if ("CHECK".equals(xAuthenticatedHeader)) {
            // YES - go to the next filter if authorized
            filterChain.doFilter(request, response);
        } else {
            // NO - respond with deny page
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(ErrorMessagesEnum.REQUEST_NOT_FROM_ROBOT.getMessage());
        }
    }
}
