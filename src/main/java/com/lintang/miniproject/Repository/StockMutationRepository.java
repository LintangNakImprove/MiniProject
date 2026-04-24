package com.lintang.miniproject.Repository;

import com.lintang.miniproject.Model.StockMutation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockMutationRepository extends JpaRepository <StockMutation, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE StockMutation s SET s.product = null WHERE s.product.id = :productId")
    void nullifyProduct(Long productId);
}
