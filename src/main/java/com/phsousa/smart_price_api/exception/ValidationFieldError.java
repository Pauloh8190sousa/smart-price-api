package com.phsousa.smart_price_api.exception;

public record ValidationFieldError(
        String field,
        String message
) {
}