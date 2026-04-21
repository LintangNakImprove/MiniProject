package com.lintang.miniproject.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockMutationRequest {
    @NotNull(message = "Product Id Tidak Boleh Kosong")
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("from_warehouse_id")
    private Long fromWarehouseId;

    @JsonProperty("to_warehouse_id")
    private Long toWareHouseId;

    @NotNull(message = "Quantity Tidak Boleh Kosong")
    private Integer quantity;

    @NotBlank(message = "Type Tidak Boleh Kosong")
    private String type;

}
