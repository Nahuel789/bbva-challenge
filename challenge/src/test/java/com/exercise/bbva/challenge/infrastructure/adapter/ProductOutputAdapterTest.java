package com.exercise.bbva.challenge.infrastructure.adapter;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.ProductJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static com.exercise.bbva.challenge.util.TestUtil.getProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductOutputAdapterTest {

    @Mock
    ProductJPARepository productJPARepository;

    @InjectMocks
    ProductOutputAdapter adapter;

    @Test
    void getAllByIds_withValidIds_returnProductList() {
        Set<Long> ids = Set.of(1L, 2L);
        List<BankProduct> products = List.of(getProduct(1L, "Cuenta Corriente"), getProduct(2L, "Caja de Ahorro"));

        when(productJPARepository.findAllById(ids)).thenReturn(products);

        List<BankProduct> result = adapter.getAllByIds(ids);

        assertEquals(2, result.size());
        assertEquals("Caja de Ahorro", result.get(1).getProduct());
    }

    @Test
    void findByProduct_withValidId_returnProduct() {
        String productName = "TJDEBITO";
        BankProduct product = getProduct(3L, productName);

        when(productJPARepository.findByProduct(productName)).thenReturn(product);

        BankProduct result = adapter.findByProduct(productName);

        assertNotNull(result);
        assertEquals(productName, result.getProduct());
    }

    @Test
    void createProduct_withValidProduct_returnProductCreated() {
        BankProduct product = getProduct(null, "TJCREDITO");
        BankProduct savedProduct = getProduct(4L, "TJCREDITO");

        when(productJPARepository.save(product)).thenReturn(savedProduct);

        BankProduct result = adapter.createProduct(product);

        assertNotNull(result);
        assertEquals(4L, result.getId());
    }

    @Test
    void getAllProducts_withData_returnProductList() {
        List<BankProduct> products = List.of(getProduct(1L, "Cuenta Corriente"));

        when(productJPARepository.findAll()).thenReturn(products);

        List<BankProduct> result = adapter.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Cuenta Corriente", result.get(0).getProduct());
    }
}