package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.ProductService;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void createProduct_withValidProductDTO_returnCreatedProduct() {
        ProductDTO productDTO = getProductDTO();

        Mockito.when(productService.createProduct(Mockito.any(ProductDTO.class)))
                .thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Cuenta Corriente", response.getBody().getProduct());
    }

    @Test
    void getAllProducts_withoutError_returnProductList() {
        List<ProductDTO> productList = List.of(getProductDTO());

        Mockito.when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        assertEquals("Cuenta Corriente", response.getBody().get(0).getProduct());
    }

    private ProductDTO getProductDTO() {
        return ProductDTO.builder().product("Cuenta Corriente").build();
    }
}