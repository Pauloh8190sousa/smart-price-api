package com.phsousa.smart_price_api.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ProductResponseDTO(
        UUID id,
        String name,
        String slug,
        String brand,
        String model,
        String category,
        String imageUrl,
        String description,
        Boolean active,
        LocalDateTime createdAt
) {
}
