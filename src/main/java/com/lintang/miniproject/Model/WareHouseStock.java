package com.lintang.miniproject.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WareHouseStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;

    private Integer stock;

    @Version
    private Long version;
}
