package com.lintang.miniproject.Repository;

import com.lintang.miniproject.Model.Product;
import com.lintang.miniproject.Model.WareHouse;
import com.lintang.miniproject.Model.WareHouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WareHouseStockRepository extends JpaRepository <WareHouseStock, Long> {
    Optional<WareHouseStock> findByProductAndWareHouse(Product product, WareHouse wareHouse);
    // Hapus query getTotalStockBySku, ganti dengan ini
    List<WareHouseStock> findByProduct(Product product);
    // Low stock
    @Query("SELECT w FROM WareHouseStock w WHERE w.stock < 10 AND w.product.isActive = true")
    List<WareHouseStock> findLowStock();

}
