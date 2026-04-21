package com.lintang.miniproject.Request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WareHouseStockUpdate {
    @NotNull(message = "Stok Tidak Boleh 0")
    private Integer stock;
}
