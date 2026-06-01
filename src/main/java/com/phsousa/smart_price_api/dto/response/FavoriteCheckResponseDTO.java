package com.phsousa.smart_price_api.dto.response;

import java.util.UUID;

public record FavoriteCheckResponseDTO(
        Boolean favorited,
        UUID favoriteId
) {
}
