package com.lintang.miniproject.Controller;

import com.lintang.miniproject.Model.WareHouse;
import com.lintang.miniproject.Request.WareHouseRequest;
import com.lintang.miniproject.Response.WebResponse;
import com.lintang.miniproject.Service.WareHouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/WareHouse")
public class WareHouseController {
    private final WareHouseService wareHouseService;
    public WareHouseController (WareHouseService wareHouseService){
        this.wareHouseService = wareHouseService;
    }

//    Create
    @PostMapping
    public WebResponse<WareHouse> addWareHouse(@Valid @RequestBody WareHouseRequest wareHouseRequest){
        return WebResponse.<WareHouse>builder()
                .status("Berhasil")
                .message("WareHouse Berhasil Di Tambahkan")
                .data(wareHouseService.tambahWareHouse(wareHouseRequest))
                .build();
    }

//    Get
    @GetMapping
    public List <WareHouse> semuaDataWareHouse(){
        return wareHouseService.getWareHouse();
    }

//    Get ById
    @GetMapping("/{id}")
    public WebResponse <WareHouse> dataWareHouseById(@PathVariable Long id){
        WareHouse wareHouse = wareHouseService.getWareHouseById(id);
        return new WebResponse<>("Done", "Data WareHouse : ", wareHouse);
    }

//    Update
    @PutMapping("/{id}")
    public ResponseEntity <WebResponse <WareHouse>> updateWareHouse(@PathVariable Long id, @Valid @RequestBody WareHouseRequest wareHouseRequest){
        WareHouse wareHouse = wareHouseService.updateWareHouse(id, wareHouseRequest);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.<WareHouse>builder()
                        .status("Berhasil")
                        .message("Gudang Berhasil Di Update")
                        .data(wareHouse)
                        .build()
        );
    }

//    Delete
    @DeleteMapping("{id}")
    public String deleteWareHouse(@PathVariable Long id){
        wareHouseService.deleteWareHouse(id);
        return "WareHouse Dengan Id : " + id + " Berhasil Di Hapus";
    }
}
