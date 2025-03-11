package com.hackathon.smallparkingpassdemo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Check if the request is for an Actuator endpoint
        if (request.getRequestURI().startsWith("/actuator")) {
            // Allow Actuator endpoints to proceed without x-authenticated header check
            filterChain.doFilter(request, response);
            return;
        }

        String xAuthenticatedHeader = request.getHeader("X-HACKATHON");

        // check for correct http header
        if ("authenticated".equals(xAuthenticatedHeader)) {
            // YES - go to the next filter if authorized
            filterChain.doFilter(request, response);
        } else {
            // NO - respond with deny page
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(ErrorMessagesEnum.REQUEST_PROHIBITED.getMessage());
        }
    }
}
