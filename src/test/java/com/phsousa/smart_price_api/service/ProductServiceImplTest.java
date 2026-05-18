package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.response.ProductResponseDTO;
import com.phsousa.smart_price_api.entity.Product;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.repository.ProductRepository;
import com.phsousa.smart_price_api.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_shouldReturnProductResponse() {

        Product product = Product.builder()
                .name("iPhone 15 Pro")
                .slug("iphone-15-pro")
                .brand("Apple")
                .build();

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> {
                    Product p = invocation.getArgument(0);
                    p.setId(UUID.randomUUID());
                    return p;
                });

        ProductResponseDTO response = productService.create(product);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("iPhone 15 Pro");
        assertThat(response.slug()).isEqualTo("iphone-15-pro");
        assertThat(response.brand()).isEqualTo("Apple");

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void findById_shouldReturnProduct_whenExists() {

        UUID id = UUID.randomUUID();

        Product product = Product.builder()
                .id(id)
                .name("Galaxy S25")
                .slug("galaxy-s25")
                .brand("Samsung")
                .active(true)
                .build();

        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        ProductResponseDTO response = productService.findById(id);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("Galaxy S25");

        verify(productRepository).findById(id);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {

        UUID id = UUID.randomUUID();

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(productRepository).findById(id);
    }

    @Test
    void findAll_shouldReturnListOfProducts() {

        Product product1 = Product.builder()
                .id(UUID.randomUUID())
                .name("iPhone 15")
                .build();

        Product product2 = Product.builder()
                .id(UUID.randomUUID())
                .name("Galaxy S25")
                .build();

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        List<ProductResponseDTO> response = productService.findAll();

        assertThat(response).hasSize(2);

        verify(productRepository).findAll();
    }

    @Test
    void delete_shouldCallRepository() {

        UUID id = UUID.randomUUID();

        doNothing()
                .when(productRepository)
                .deleteById(id);

        productService.delete(id);

        verify(productRepository).deleteById(id);
    }

    @Test
    void update_shouldReturnUpdatedProduct() {

        UUID id = UUID.randomUUID();

        Product existing = Product.builder()
                .id(id)
                .name("iPhone 14")
                .slug("iphone-14")
                .brand("Apple")
                .active(true)
                .build();

        Product updateData = Product.builder()
                .name("iPhone 15 Pro")
                .slug("iphone-15-pro")
                .brand("Apple")
                .active(true)
                .build();

        when(productRepository.findById(id))
                .thenReturn(Optional.of(existing));

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductResponseDTO response = productService.update(id, updateData);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("iPhone 15 Pro");
        assertThat(response.slug()).isEqualTo("iphone-15-pro");
        assertThat(response.brand()).isEqualTo("Apple");

        verify(productRepository).findById(id);
        verify(productRepository).save(any(Product.class));
    }
}