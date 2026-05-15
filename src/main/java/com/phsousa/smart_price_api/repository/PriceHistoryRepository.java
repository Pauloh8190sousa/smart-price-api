package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, UUID> {

    List<PriceHistory> findByProductId(UUID productId);

    List<PriceHistory> findByStoreId(UUID storeId);
}