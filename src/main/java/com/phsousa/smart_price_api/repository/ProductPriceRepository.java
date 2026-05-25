package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
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

    Optional<ProductPrice> findFirstByProductIdOrderByPriceDesc(UUID productId);

    long countDistinctStoreByProductId(UUID productId);

    @Query("""
    SELECT AVG(pp.price)
    FROM ProductPrice pp
    WHERE pp.product.id = :productId
""")
    BigDecimal findAveragePriceByProductId(UUID productId);
}