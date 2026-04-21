package com.lintang.miniproject.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    private String name;

    @Version
    private Long version;
}
