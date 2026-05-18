package com.phsousa.smart_price_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Resposta de produto")
public record ProductResponseDTO(

        @Schema(
                description = "Identificador único do produto",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Nome do produto",
                example = "iPhone 15 Pro"
        )
        String name,

        @Schema(
                description = "Slug amigável do produto",
                example = "iphone-15-pro"
        )
        String slug,

        @Schema(
                description = "Marca do produto",
                example = "Apple"
        )
        String brand,

        @Schema(
                description = "Modelo do produto",
                example = "15 Pro"
        )
        String model,

        @Schema(
                description = "Categoria do produto",
                example = "Celulares"
        )
        String category,

        @Schema(
                description = "URL da imagem do produto",
                example = "https://cdn.smartprice.com/products/iphone15pro.jpg"
        )
        String imageUrl,

        @Schema(
                description = "Descrição do produto",
                example = "iPhone 15 Pro com chip A17 Pro"
        )
        String description,

        @Schema(
                description = "Produto ativo",
                example = "true"
        )
        Boolean active,

        @Schema(
                description = "Data de criação do produto",
                example = "2026-05-18T12:00:00"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Data da última atualização do produto",
                example = "2026-05-18T14:30:00"
        )
        LocalDateTime updatedAt
) {
}