package com.lintang.miniproject.Request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WareHouseRequest {
    @NotBlank(message = "Code Tidak Boleh Kosong")
    private String code;

    @NotBlank(message = "Nama WareHouse Tidak Boleh Kosong")
    private String name;
}
