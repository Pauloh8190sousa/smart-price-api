package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.PriceAlertRequestDTO;
import com.phsousa.smart_price_api.dto.response.PriceAlertResponseDTO;
import com.phsousa.smart_price_api.service.PriceAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/price-alerts")
@RequiredArgsConstructor
public class PriceAlertController {

    private final PriceAlertService service;

    @PostMapping
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ResponseEntity<PriceAlertResponseDTO> create(
            @RequestBody @Valid PriceAlertRequestDTO dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ResponseEntity<List<PriceAlertResponseDTO>> findByUser(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(service.findByUser(userId));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    public ResponseEntity<PriceAlertResponseDTO> toggle(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.toggle(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    public ResponseEntity<String> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.ok("Alerta removido com sucesso");
    }
}
