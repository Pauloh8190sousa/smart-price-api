package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.entity.*;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.mapper.ProductPriceMapper;
import com.phsousa.smart_price_api.repository.*;
import com.phsousa.smart_price_api.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final PriceHistoryRepository priceHistoryRepository;
    private final PriceAlertRepository priceAlertRepository;

    @Override
    public ProductPriceResponseDTO create(
            ProductPriceRequestDTO dto
    ) {

        Product product = productRepository
                .findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Produto não encontrado"
                        )
                );

        Store store = storeRepository
                .findById(dto.getStoreId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loja não encontrada"
                        )
                );

        ProductPrice entity =
                ProductPriceMapper.toEntity(dto);

        entity.setProduct(product);
        entity.setStore(store);


        ProductPrice lastPrice = productPriceRepository
                .findTopByProductIdAndStoreIdOrderByCapturedAtDesc(
                        dto.getProductId(),
                        dto.getStoreId()
                )
                .orElse(null);

        // cria histórico se preço mudou
        if (lastPrice != null &&
                lastPrice.getPrice().compareTo(dto.getPrice()) != 0) {

            PriceHistory history = PriceHistory.builder()
                    .oldPrice(lastPrice.getPrice())
                    .newPrice(dto.getPrice())
                    .changedAt(LocalDateTime.now())
                    .product(product)
                    .store(store)
                    .build();

            priceHistoryRepository.save(history);
        }

        ProductPrice saved =
                productPriceRepository.save(entity);

        processPriceAlerts(saved);

        return ProductPriceMapper.toDTO(saved);
    }

    @Override
    public List<ProductPriceResponseDTO>
    findByProduct(UUID productId) {

        return productPriceRepository
                .findByProductId(productId)
                .stream()
                .map(ProductPriceMapper::toDTO)
                .toList();
    }

    @Override
    public ProductPriceResponseDTO
    findLowestPrice(UUID productId) {

        ProductPrice entity = productPriceRepository
                .findFirstByProductIdOrderByPriceAsc(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Nenhum preço encontrado"
                        )
                );

        return ProductPriceMapper.toDTO(entity);
    }

    @Override
    public List<ProductPriceResponseDTO>
    findByStore(UUID storeId) {

        return productPriceRepository
                .findByStoreId(storeId)
                .stream()
                .map(ProductPriceMapper::toDTO)
                .toList();
    }



    private void processPriceAlerts(ProductPrice productPrice) {

        List<PriceAlert> alerts =
                priceAlertRepository
                        .findByProductIdAndActiveTrue(
                                productPrice.getProduct().getId()
                        );

        for (PriceAlert alert : alerts) {

            boolean reachedTarget =
                    productPrice.getPrice()
                            .compareTo(alert.getTargetPrice()) <= 0;

            if (reachedTarget) {

                log.info("""
                        PRICE ALERT TRIGGERED
                        
                        Product: {}
                        Current Price: {}
                        Target Price: {}
                        User: {}
                        """,
                        productPrice.getProduct().getName(),
                        productPrice.getPrice(),
                        alert.getTargetPrice(),
                        alert.getUser().getEmail()
                );

                alert.setActive(false);

                priceAlertRepository.save(alert);
            }
        }
    }
}