package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.request.FavoriteProductRequestDTO;
import com.phsousa.smart_price_api.dto.response.FavoriteCheckResponseDTO;
import com.phsousa.smart_price_api.dto.response.FavoriteProductResponseDTO;
import com.phsousa.smart_price_api.entity.FavoriteProduct;
import com.phsousa.smart_price_api.entity.Product;
import com.phsousa.smart_price_api.entity.User;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.mapper.FavoriteProductMapper;
import com.phsousa.smart_price_api.repository.FavoriteProductRepository;
import com.phsousa.smart_price_api.repository.ProductRepository;
import com.phsousa.smart_price_api.repository.UserRepository;
import com.phsousa.smart_price_api.service.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteProductServiceImpl
        implements FavoriteProductService {

    private final FavoriteProductRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public FavoriteProductResponseDTO create(
            FavoriteProductRequestDTO dto
    ) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"
                        )
                );

        Product product = productRepository
                .findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Produto não encontrado"
                        )
                );

        repository.findByUserIdAndProductId(
                dto.getUserId(),
                dto.getProductId()
        ).ifPresent(favorite -> {
            throw new IllegalArgumentException(
                    "Produto já favoritado"
            );
        });

        FavoriteProduct entity =
                FavoriteProduct.builder()
                        .user(user)
                        .product(product)
                        .build();

        repository.save(entity);

        return FavoriteProductMapper.toDTO(entity);
    }

    @Override
    public List<FavoriteProductResponseDTO> findByUser(
            UUID userId
    ) {

        return repository.findByUserId(userId)
                .stream()
                .map(FavoriteProductMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        FavoriteProduct favorite = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Favorito não encontrado"
                        )
                );

        repository.delete(favorite);
    }



    @Override
    public FavoriteCheckResponseDTO check(
            UUID userId,
            UUID productId
    ) {

        return repository
                .findByUserIdAndProductId(userId, productId)
                .map(favorite ->
                        new FavoriteCheckResponseDTO(
                                true,
                                favorite.getId()
                        )
                )
                .orElse(
                        new FavoriteCheckResponseDTO(
                                false,
                                null
                        )
                );
    }
}