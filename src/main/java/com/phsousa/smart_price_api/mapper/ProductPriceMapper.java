package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.entity.ProductPrice;

import java.time.LocalDateTime;

public class ProductPriceMapper {

    public static ProductPrice toEntity(ProductPriceRequestDTO dto) {

        return ProductPrice.builder()
                .price(dto.getPrice())
                .productUrl(dto.getProductUrl())
                .available(dto.getAvailable())
                .sellerName(dto.getSellerName())
                .shippingPrice(dto.getShippingPrice())
                .installmentQuantity(dto.getInstallmentQuantity())
                .installmentValue(dto.getInstallmentValue())
                .capturedAt(LocalDateTime.now())
                .build();
    }

    public static ProductPriceResponseDTO toDTO(ProductPrice entity) {

        return new ProductPriceResponseDTO(
                entity.getId(),
                entity.getPrice(),
                entity.getProductUrl(),
                entity.getAvailable(),
                entity.getSellerName(),
                entity.getShippingPrice(),
                entity.getInstallmentQuantity(),
                entity.getInstallmentValue(),
                entity.getCapturedAt(),

                entity.getProduct().getId(),
                entity.getProduct().getName(),

                entity.getStore().getId(),
                entity.getStore().getName()
        );
    }
}