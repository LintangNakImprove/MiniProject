package com.lintang.miniproject.Repository;

import com.lintang.miniproject.Model.StockMutation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMutationRepository extends JpaRepository <StockMutation, Long> {
}
