package com.phsousa.smart_price_api.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "Slug é obrigatório")
    private String slug;

    private String brand;
    private String model;
    private String category;
    private String imageUrl;

    @Size(max = 2000)
    private String description;
}