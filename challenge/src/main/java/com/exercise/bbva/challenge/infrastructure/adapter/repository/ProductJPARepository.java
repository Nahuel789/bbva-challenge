package com.exercise.bbva.challenge.infrastructure.adapter.repository;

import com.exercise.bbva.challenge.domain.BankProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<BankProduct, Long> {
    BankProduct findByProduct(String product);
}
