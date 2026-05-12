package com.phsousa.smart_price_api.mapper;
import com.phsousa.smart_price_api.dto.request.ProductRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductResponseDTO;
import com.phsousa.smart_price_api.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {

        return Product.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .description(dto.getDescription())
                .build();
    }

    public static ProductResponseDTO toDTO(Product product) {

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .brand(product.getBrand())
                .model(product.getModel())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .build();
    }
}