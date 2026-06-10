package com.phsousa.smart_price_api.dto.request;

import com.phsousa.smart_price_api.util.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
            regexp = ValidationConstants.SLUG,
            message = "Slug inválido"
    )
    private String slug;

    @NotBlank(message = "Marca é obrigatória")
    @Schema(
            description = "Marca do produto",
            example = "Apple"
    )
    private String brand;

    @NotBlank(message = "Modelo é obrigatório")
    @Schema(
            description = "Modelo do produto",
            example = "15 Pro"
    )
    private String model;


    @NotBlank(message = "Categoria é obrigatória")
    @Schema(
            description = "Categoria do produto",
            example = "Celulares"
    )
    private String category;

    @Schema(
            description = "URL da imagem do produto",
            example = "https://cdn.smartprice.com/products/iphone15pro.jpg"
    )
    @Pattern(
            regexp = ValidationConstants.HTTP_URL,
            message = "URL da imagem inválida"
    )
    private String imageUrl;

    @Size(max = 2000)
    @Schema(
            description = "Descrição detalhada do produto",
            example = "iPhone 15 Pro com chip A17 Pro e câmera avançada"
    )
    private String description;
}