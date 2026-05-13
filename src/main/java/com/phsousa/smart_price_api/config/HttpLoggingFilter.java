package com.phsousa.smart_price_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper requestWrapper =
                new ContentCachingRequestWrapper(request, 1024 * 1024);

        ContentCachingResponseWrapper responseWrapper =
                new ContentCachingResponseWrapper(response);

        try {

            filterChain.doFilter(requestWrapper, responseWrapper);

        } finally {

            long duration = System.currentTimeMillis() - startTime;

            String requestBody = new String(
                    requestWrapper.getContentAsByteArray(),
                    StandardCharsets.UTF_8
            );

            String responseBody = new String(
                    responseWrapper.getContentAsByteArray(),
                    StandardCharsets.UTF_8
            );

            requestBody = requestBody.replaceAll(
                    "\"password\":\"(.*?)\"",
                    "\"password\":\"***\""
            );

            responseBody = responseBody.replaceAll(
                    "\"token\":\"(.*?)\"",
                    "\"token\":\"***\""
            );

            log.info("""
                    
                    ==========================
                    HTTP REQUEST
                    Method: {}
                    URI: {}
                    Query: {}
                    Request Body: {}
                    
                    HTTP RESPONSE
                    Status: {}
                    Response Body: {}
                    
                    Duration: {} ms
                    ==========================
                    """,
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getQueryString(),
                    requestBody,
                    responseWrapper.getStatus(),
                    responseBody,
                    duration
            );

            responseWrapper.copyBodyToResponse();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/favicon.ico");
    }
}