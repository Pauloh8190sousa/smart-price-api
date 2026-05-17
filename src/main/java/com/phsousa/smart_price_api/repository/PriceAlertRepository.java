package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.PriceAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceAlertRepository extends JpaRepository<PriceAlert, UUID> {

    List<PriceAlert> findByUserId(UUID userId);

    List<PriceAlert> findByProductId(UUID productId);

    List<PriceAlert> findByProductIdAndActiveTrue(UUID productId);

    List<PriceAlert> findByActiveTrue();
}
