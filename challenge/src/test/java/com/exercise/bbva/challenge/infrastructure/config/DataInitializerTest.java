package com.exercise.bbva.challenge.infrastructure.config;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.ProductJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DataInitializerTest {

    private ProductJPARepository repository;
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        repository = mock(ProductJPARepository.class);
        dataInitializer = new DataInitializer();
    }

    @Test
    void initBankProduct_withEmptyRepo_generateRows() throws Exception {
        when(repository.count()).thenReturn(0L);
        ArgumentCaptor<List<BankProduct>> captor = ArgumentCaptor.forClass(List.class);

        dataInitializer.initBankProducts(repository).run();

        verify(repository, times(1)).saveAll(captor.capture());
        List<BankProduct> savedProducts = captor.getValue();

        assertThat(savedProducts).hasSize(4);
        assertThat(savedProducts).extracting(BankProduct::getProduct)
                .containsExactlyInAnyOrder("CHEQ", "TJDEBITO", "TJCREDITO", "PZOF");
    }

    @Test
    void initBankProduct_withRepoFully_neverSaveRows() throws Exception {
        when(repository.count()).thenReturn(4L);

        dataInitializer.initBankProducts(repository).run();

        verify(repository, never()).saveAll(any());
    }

}