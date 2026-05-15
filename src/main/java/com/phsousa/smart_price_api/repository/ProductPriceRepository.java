package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, UUID> {

    List<ProductPrice> findByProductId(UUID productId);

    List<ProductPrice> findByStoreId(UUID storeId);

    Optional<ProductPrice>
    findFirstByProductIdOrderByPriceAsc(UUID productId);

    Optional<ProductPrice>
    findTopByProductIdAndStoreIdOrderByCapturedAtDesc(
            UUID productId,
            UUID storeId
    );
}