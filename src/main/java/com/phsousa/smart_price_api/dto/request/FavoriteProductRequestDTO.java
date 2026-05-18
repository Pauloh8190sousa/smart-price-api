package com.phsousa.smart_price_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Dados para favoritar um produto")
public class FavoriteProductRequestDTO {
    @NotNull(message = "Usuário é obrigatório")
    @Schema(
            description = "ID do usuário",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID userId;

    @NotNull(message = "Produto é obrigatório")
    @Schema(
            description = "ID do produto",
            example = "550e8400-e29b-41d4-a716-446655440111"
    )
    private UUID productId;
}