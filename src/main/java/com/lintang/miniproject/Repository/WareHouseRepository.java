package com.lintang.miniproject.Repository;

import com.lintang.miniproject.Model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository <WareHouse, Long> {
    boolean existsByCode(String code);
    boolean existsByCodeAndIdNot(String code, Long id);
}
