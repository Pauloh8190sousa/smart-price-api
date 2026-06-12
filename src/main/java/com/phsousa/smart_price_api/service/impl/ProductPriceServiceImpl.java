package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceStatsResponseDTO;
import com.phsousa.smart_price_api.entity.*;
import com.phsousa.smart_price_api.exception.BusinessException;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.mapper.ProductPriceMapper;
import com.phsousa.smart_price_api.repository.*;
import com.phsousa.smart_price_api.service.EmailService;
import com.phsousa.smart_price_api.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final EmailService emailService;

    @Override
    @Transactional
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

        if (!product.getActive()) {
            throw new BusinessException(
                    "Produto está inativo"
            );
        }

        Store store = storeRepository
                .findById(dto.getStoreId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loja não encontrada"
                        )
                );

        if (!store.getActive()) {
            throw new BusinessException(
                    "Loja está inativa"
            );
        }


        validateProductPriceDTO(dto);

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


        if (lastPrice != null && isDuplicatePrice(lastPrice, dto)) {
            return ProductPriceMapper.toDTO(lastPrice);
        }


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

        if (lastPrice == null) {

            PriceHistory history = PriceHistory.builder()
                    .oldPrice(dto.getPrice())
                    .newPrice(dto.getPrice())
                    .changedAt(LocalDateTime.now())
                    .product(product)
                    .store(store)
                    .build();

            priceHistoryRepository.save(history);
        }

        processPriceAlerts(saved);

        return ProductPriceMapper.toDTO(saved);
    }

    @Override
    public List<ProductPriceResponseDTO>
    findByProduct(UUID productId) {

        return productPriceRepository
                .findByProductIdOrderByPriceAsc(productId)
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

        if (!productPrice.getAvailable()) {
            return;
        }

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

                if (alert.getUser().getEmail() == null || alert.getUser().getEmail().isBlank()) {
                    continue;
                }

                emailService.sendPriceAlert(
                        alert.getUser().getEmail(),
                        productPrice.getProduct().getName(),
                        productPrice.getPrice(),
                        alert.getTargetPrice()
                );

                alert.setActive(false);

                priceAlertRepository.save(alert);
            }
        }
    }

    @Override
    public ProductPriceStatsResponseDTO getStats(UUID productId) {

        BigDecimal lowest =
                productPriceRepository
                        .findFirstByProductIdOrderByPriceAsc(productId)
                        .map(ProductPrice::getPrice)
                        .orElse(BigDecimal.ZERO);

        BigDecimal highest =
                productPriceRepository
                        .findFirstByProductIdOrderByPriceDesc(productId)
                        .map(ProductPrice::getPrice)
                        .orElse(BigDecimal.ZERO);

        BigDecimal average =
                Optional.ofNullable(
                        productPriceRepository
                                .findAveragePriceByProductId(productId)
                ).orElse(BigDecimal.ZERO);

        long storesCount =
                productPriceRepository
                        .countDistinctStoreByProductId(productId);

        return new ProductPriceStatsResponseDTO(
                lowest,
                highest,
                average,
                storesCount
        );
    }

    private void validateProductPriceDTO(ProductPriceRequestDTO dto) {

        boolean hasQuantity =
                dto.getInstallmentQuantity() != null;

        boolean hasValue =
                dto.getInstallmentValue() != null;

        if (hasQuantity != hasValue) {
            throw new BusinessException(
                    "Parcelamento inválido"
            );
        }

        if (dto.getInstallmentQuantity() != null
                && dto.getInstallmentQuantity() <= 0) {

            throw new BusinessException(
                    "Quantidade de parcelas inválida"
            );
        }

        if (dto.getInstallmentValue() != null
                && dto.getInstallmentValue().compareTo(BigDecimal.ZERO) <= 0) {

            throw new BusinessException(
                    "Valor da parcela inválido"
            );
        }


        if (
                dto.getInstallmentQuantity() != null
        ) {

            BigDecimal totalInstallments =
                    dto.getInstallmentValue()
                            .multiply(
                                    BigDecimal.valueOf(
                                            dto.getInstallmentQuantity()
                                    )
                            );

            if (
                    totalInstallments.compareTo(
                            dto.getPrice().multiply(
                                    BigDecimal.valueOf(1.5)
                            )
                    ) > 0
            ) {

                throw new BusinessException(
                        "Parcelamento incompatível com o preço"
                );
            }
        }



        if (dto.getShippingPrice() != null
                && dto.getShippingPrice().compareTo(BigDecimal.ZERO) < 0) {

            throw new BusinessException(
                    "Frete inválido"
            );
        }

        if (dto.getPrice().compareTo(
                BigDecimal.valueOf(500_000)
        ) > 0) {

            throw new BusinessException(
                    "Preço fora do limite permitido"
            );
        }

        if (!dto.getAvailable()
                && dto.getInstallmentQuantity() != null) {

            throw new BusinessException(
                    "Produto indisponível não pode possuir parcelamento"
            );
        }

        if (dto.getProductUrl().length() > 1000) {
            throw new BusinessException(
                    "URL excede o limite permitido"
            );
        }
    }

    private boolean isDuplicatePrice(ProductPrice lastPrice, ProductPriceRequestDTO dto) {
        boolean samePrice =
                lastPrice.getPrice().compareTo(dto.getPrice()) == 0;

        boolean sameAvailability =
                lastPrice.getAvailable().equals(dto.getAvailable());

        boolean sameShipping =
                Objects.equals(
                        lastPrice.getShippingPrice(),
                        dto.getShippingPrice()
                );

        boolean sameInstallmentQuantity =
                Objects.equals(
                        lastPrice.getInstallmentQuantity(),
                        dto.getInstallmentQuantity()
                );

        boolean sameInstallmentValue =
                Objects.equals(
                        lastPrice.getInstallmentValue(),
                        dto.getInstallmentValue()
                );

        boolean sameUrl =
                Objects.equals(
                        lastPrice.getProductUrl(),
                        dto.getProductUrl()
                );

        boolean sameSeller =
                Objects.equals(
                        lastPrice.getSellerName(),
                        dto.getSellerName()
                );

        if (
                samePrice
                        && sameAvailability
                        && sameShipping
                        && sameInstallmentQuantity
                        && sameInstallmentValue
                        && sameUrl
                        && sameSeller
        ) {
            return true;
        }

        return false;
    }
}