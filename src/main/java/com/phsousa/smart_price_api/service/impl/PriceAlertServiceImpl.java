package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.request.PriceAlertRequestDTO;
import com.phsousa.smart_price_api.dto.response.PriceAlertResponseDTO;
import com.phsousa.smart_price_api.entity.PriceAlert;
import com.phsousa.smart_price_api.entity.Product;
import com.phsousa.smart_price_api.entity.User;
import com.phsousa.smart_price_api.mapper.PriceAlertMapper;
import com.phsousa.smart_price_api.repository.PriceAlertRepository;
import com.phsousa.smart_price_api.repository.ProductRepository;
import com.phsousa.smart_price_api.repository.UserRepository;
import com.phsousa.smart_price_api.service.PriceAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PriceAlertServiceImpl implements PriceAlertService {

    private final PriceAlertRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public PriceAlertResponseDTO create(PriceAlertRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        PriceAlert alert = PriceAlert.builder()
                .targetPrice(dto.getTargetPrice())
                .active(true)
                .createdAt(LocalDateTime.now())
                .user(user)
                .product(product)
                .build();

        repository.save(alert);

        return PriceAlertMapper.toDTO(alert);
    }

    @Override
    public List<PriceAlertResponseDTO> findByUser(UUID userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(PriceAlertMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public PriceAlertResponseDTO toggle(UUID id) {

        PriceAlert alert = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setActive(!alert.getActive());

        repository.save(alert);

        return PriceAlertMapper.toDTO(alert);
    }

}
