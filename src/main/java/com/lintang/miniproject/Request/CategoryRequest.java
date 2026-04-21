package com.lintang.miniproject.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Nama Category Tidak Boleh Kosong")
    private String name;
}
