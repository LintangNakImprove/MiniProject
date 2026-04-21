package com.lintang.miniproject.Controller;

import com.lintang.miniproject.Model.WareHouseStock;
import com.lintang.miniproject.Request.WareHouseStockCreate;
import com.lintang.miniproject.Request.WareHouseStockUpdate;
import com.lintang.miniproject.Response.WebResponse;
import com.lintang.miniproject.Service.WareHouseStockService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Stock")
public class WareHouseStockController {
    private final WareHouseStockService wareHouseStockService;
    public WareHouseStockController(WareHouseStockService wareHouseStockService){
        this.wareHouseStockService = wareHouseStockService;
    }

//    Create
    @PostMapping
    public WebResponse<WareHouseStock> addWareHouseStock(@Valid @RequestBody WareHouseStockCreate wareHouseStockCreate){
        return WebResponse.<WareHouseStock>builder()
                .status("Berhasil")
                .message("WareHouseStock Berhasil Di Tambahkan")
                .data(wareHouseStockService.tambahWareHouseStock(wareHouseStockCreate))
                .build();
    }
//    Get
    @GetMapping
    public List <WareHouseStock> semuaDataWareHouseStock(){
        return wareHouseStockService.getWareHouseStock();
    }
//  Get ById
    @GetMapping("/{id}")
    public WebResponse <WareHouseStock> dataWareHouseStockById(@PathVariable Long id){
        WareHouseStock wareHouseStock = wareHouseStockService.getWareHouseStockById(id);
        return new WebResponse<>("Done", "Data : ", wareHouseStock );
    }
//  Update
    @PutMapping("/{id}")
    public WebResponse <WareHouseStock> updateWareHouseStock (@PathVariable Long id,@Valid @RequestBody WareHouseStockUpdate wareHouseStockUpdate){
        WareHouseStock wareHouseStock = wareHouseStockService.updateWareHouseStock(id, wareHouseStockUpdate);
        return new WebResponse<>("Done", "WareHouseStock Berhasil Di Update", wareHouseStock);
    }
//    Delete
    @DeleteMapping("{id}")
    public String deleteWareHouseStock(@PathVariable Long id){
        wareHouseStockService.deleteWareHouseStock(id);
        return "WareHouse Stock Berhasil Di Hapus";
    }

    // Get
    @GetMapping("/low-stock")
    public WebResponse<List<WareHouseStock>> getLowStock(){
        List<WareHouseStock> lowStock = wareHouseStockService.getLowStock();
        return new WebResponse<>("success", "Data Low Stock Berhasil Di Ambil", lowStock);
    }

    @GetMapping("/totalStok/{sku}")
    public WebResponse <Integer> getTotalBySku(@PathVariable String sku){
        Integer total = wareHouseStockService.getStockSummaryBySku(sku);
        return new WebResponse<>("success", "Data Low Stock Berhasil Di Ambil", total);
    }
}
