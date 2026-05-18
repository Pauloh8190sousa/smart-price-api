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

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getBrand(),
                product.getModel(),
                product.getCategory(),
                product.getImageUrl(),
                product.getDescription(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}