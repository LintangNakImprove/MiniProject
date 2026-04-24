package com.lintang.miniproject.Repository;

import com.lintang.miniproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {
    Optional<Product> findBySkuCode(String skuCode);
    List<Product> findByIsActive(Boolean isActive);
}
