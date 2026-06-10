package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.entity.ProductPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ProductPriceMapper {

    public static ProductPrice toEntity(ProductPriceRequestDTO dto) {

        return ProductPrice.builder()
                .price(dto.getPrice())
                .productUrl(dto.getProductUrl())
                .available(dto.getAvailable())
                .sellerName(dto.getSellerName())
                .shippingPrice(Optional.ofNullable(dto.getShippingPrice())
                        .orElse(BigDecimal.ZERO))
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
                entity.getStore().getName(),
                entity.getProduct().getSlug(),
                entity.getStore().getLogoUrl()
        );
    }
}