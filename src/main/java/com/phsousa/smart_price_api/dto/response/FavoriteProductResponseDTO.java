package com.phsousa.smart_price_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Resposta de produto favorito")
public record FavoriteProductResponseDTO(

        @Schema(
                description = "ID do favorito",
                example = "550e8400-e29b-41d4-a716-446655440222"
        )
        UUID id,

        @Schema(
                description = "ID do usuário",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID userId,

        @Schema(
                description = "ID do produto",
                example = "550e8400-e29b-41d4-a716-446655440111"
        )
        UUID productId,

        @Schema(
                description = "Nome do produto",
                example = "iPhone 15 Pro"
        )
        String productName
) {
}