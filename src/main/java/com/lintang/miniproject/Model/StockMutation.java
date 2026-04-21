package com.lintang.miniproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Data
public class StockMutation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "from_warehouse_id")
    private WareHouse fromWareHouse;

    @ManyToOne
    @JoinColumn(name = "to_warehouse_id")
    private WareHouse toWareHouse;

    private Integer quantity;

    private String type;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false,updatable = false)
    private LocalDateTime timestamp;

    @Version
    private Long version;
}
