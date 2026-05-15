package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.response.PriceHistoryResponseDTO;
import com.phsousa.smart_price_api.service.PriceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/price-history")
@RequiredArgsConstructor
public class PriceHistoryController {

    private final PriceHistoryService priceHistoryService;

    @GetMapping("/product/{productId}")
    @PreAuthorize(
            "hasAuthority('PRODUCT_READ') and hasAuthority('STORE_READ')"
    )
    public ResponseEntity<List<PriceHistoryResponseDTO>>
    findByProduct(
            @PathVariable UUID productId
    ) {

        return ResponseEntity.ok(
                priceHistoryService.findByProduct(productId)
        );
    }

    @GetMapping("/store/{storeId}")
    @PreAuthorize(
            "hasAuthority('STORE_READ') and hasAuthority('PRODUCT_READ')"
    )
    public ResponseEntity<List<PriceHistoryResponseDTO>>
    findByStore(
            @PathVariable UUID storeId
    ) {

        return ResponseEntity.ok(
                priceHistoryService.findByStore(storeId)
        );
    }
}
