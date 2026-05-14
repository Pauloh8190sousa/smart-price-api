package com.phsousa.smart_price_api.dto.response;

import java.util.UUID;

public record StoreResponseDTO(
        UUID id,
        String name,
        String websiteUrl,
        String logoUrl,
        Boolean active
) {}
