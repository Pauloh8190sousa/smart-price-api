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
                description = "Slug do produto",
                example = "iphone-15-pro"
        )
        String productSlug,

        @Schema(
                description = "Url da imagem do produto",
                example = "https://cdn.smartprice.com/products/iphone15pro.jpg"
        )
        String productImageUrl,

        @Schema(
                description = "Nome do produto",
                example = "iPhone 15 Pro"
        )
        String productName
) {
}