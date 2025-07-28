package com.exercise.bbva.challenge.infrastructure.config;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.ProductJPARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initBankProducts(ProductJPARepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new BankProduct(null, "CHEQ", null),
                        new BankProduct(null, "TJDEBITO", null),
                        new BankProduct(null, "TJCREDITO", null),
                        new BankProduct(null, "PZOF", null)
                ));
            }
        };
    }
}

