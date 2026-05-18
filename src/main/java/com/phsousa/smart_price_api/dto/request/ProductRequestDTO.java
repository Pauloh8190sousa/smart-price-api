package com.phsousa.smart_price_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Dados para criação ou atualização de produto")
public class ProductRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 255)
    @Schema(
            description = "Nome do produto",
            example = "iPhone 15 Pro"
    )
    private String name;

    @NotBlank(message = "Slug é obrigatório")
    @Schema(
            description = "Slug amigável do produto",
            example = "iphone-15-pro"
    )
    private String slug;

    @Schema(
            description = "Marca do produto",
            example = "Apple"
    )
    private String brand;

    @Schema(
            description = "Modelo do produto",
            example = "15 Pro"
    )
    private String model;

    @Schema(
            description = "Categoria do produto",
            example = "Celulares"
    )
    private String category;

    @Schema(
            description = "URL da imagem do produto",
            example = "https://cdn.smartprice.com/products/iphone15pro.jpg"
    )
    private String imageUrl;

    @Size(max = 2000)
    @Schema(
            description = "Descrição detalhada do produto",
            example = "iPhone 15 Pro com chip A17 Pro e câmera avançada"
    )
    private String description;
}