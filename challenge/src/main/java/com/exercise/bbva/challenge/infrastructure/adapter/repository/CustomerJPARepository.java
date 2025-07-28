package com.exercise.bbva.challenge.infrastructure.adapter.repository;

import com.exercise.bbva.challenge.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c JOIN c.bankProducts p WHERE p.product = :product")
    List<Customer> findAllByProduct(@Param("product") String product);
}
