package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.PriceAlertRequestDTO;
import com.phsousa.smart_price_api.dto.response.PriceAlertResponseDTO;
import com.phsousa.smart_price_api.service.PriceAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/price-alerts")
@RequiredArgsConstructor
public class PriceAlertController {

    private final PriceAlertService service;

    @PostMapping
    public ResponseEntity<PriceAlertResponseDTO> create(
            @RequestBody @Valid PriceAlertRequestDTO dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PriceAlertResponseDTO>> findByUser(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(service.findByUser(userId));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<PriceAlertResponseDTO> toggle(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.toggle(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
