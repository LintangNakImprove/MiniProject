package com.lintang.miniproject.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class WareHouseStockCreate {
    @NotNull(message = "Product Id Tidak Boleh Kosong")
    @JsonProperty("product_id")
    private Long productId;

    @NotNull(message = "Warehouse Id Tidak")
    @JsonProperty("warehouse_id")
    private Long wareHouseId;

    @NotNull(message = "Stok Tidak Boleh 0")
    private Integer stock;
    }
