package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, UUID> {

    List<FavoriteProduct> findByUserId(UUID userId);

    Optional<FavoriteProduct> findByUserIdAndProductId(UUID userId, UUID productId);
}