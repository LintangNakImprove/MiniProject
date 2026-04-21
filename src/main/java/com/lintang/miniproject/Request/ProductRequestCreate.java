package com.lintang.miniproject.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestCreate {
    @NotBlank(message = "Nama Produk Tidak Boleh Kosong")
    private String name;

    @Min(value = 1000, message = "Harga Minimal 1000")
    private double price;

    @NotBlank(message = "Kode sku Tidak Boleh Kosong")
    private String sku_code;

    @NotNull(message = "Category id Tidak Boleh Kosong")
    @JsonProperty("category_id")
    private Long categoryId;
}
