package com.exercise.bbva.challenge.infrastructure.mapper;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductMapperTest {

    @Test
    void mapDtoToBank_withValidData_returnBank() {
        ProductDTO dto = ProductDTO.builder()
                .id(1L)
                .product("Cuenta Corriente")
                .build();

        BankProduct entity = ProductMapper.map(dto);

        assertNotNull(entity);
        assertEquals("Cuenta Corriente", entity.getProduct());
        assertNull(entity.getId());
    }

    @Test
    void mapBankToDTO_withValidData_returnDTO() {
        BankProduct entity = BankProduct.builder()
                .id(1L)
                .product("Caja de Ahorro")
                .build();

        ProductDTO dto = ProductMapper.map(entity);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Caja de Ahorro", dto.getProduct());
    }


}