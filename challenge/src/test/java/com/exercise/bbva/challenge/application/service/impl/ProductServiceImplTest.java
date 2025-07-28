package com.exercise.bbva.challenge.application.service.impl;

import com.exercise.bbva.challenge.application.port.ProductOutputPort;
import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductOutputPort productOutputPort;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_withValidInput_returnProductCreated() {
        ProductDTO inputDto = ProductDTO.builder()
                .id(null)
                .product("Cuenta Corriente")
                .build();

        BankProduct savedProduct = BankProduct.builder()
                .id(1L)
                .product("Cuenta Corriente")
                .build();

        when(productOutputPort.createProduct(any())).thenReturn(savedProduct);

        ProductDTO result = productService.createProduct(inputDto);

        assertNotNull(result);
        assertEquals("Cuenta Corriente", result.getProduct());
        assertEquals(1L, result.getId());
        verify(productOutputPort).createProduct(any());
    }

    @Test
    void shouldGetProducts_withValidInput_returnList() {
        BankProduct p1 = BankProduct.builder().id(1L).product("Plazo Fijo").build();
        BankProduct p2 = BankProduct.builder().id(2L).product("Caja de Ahorro").build();

        when(productOutputPort.getAllProducts()).thenReturn(List.of(p1, p2));

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Plazo Fijo", result.get(0).getProduct());
        assertEquals("Caja de Ahorro", result.get(1).getProduct());
    }

}