package com.phsousa.smart_price_api.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StandardError(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}