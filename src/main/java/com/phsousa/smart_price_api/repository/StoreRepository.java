package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
