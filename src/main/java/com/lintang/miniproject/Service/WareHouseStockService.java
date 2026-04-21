package com.lintang.miniproject.Service;

import com.lintang.miniproject.Model.Product;
import com.lintang.miniproject.Model.WareHouse;
import com.lintang.miniproject.Model.WareHouseStock;
import com.lintang.miniproject.Repository.ProductRepository;
import com.lintang.miniproject.Repository.WareHouseRepository;
import com.lintang.miniproject.Repository.WareHouseStockRepository;
import com.lintang.miniproject.Request.WareHouseStockCreate;
import com.lintang.miniproject.Request.WareHouseStockUpdate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WareHouseStockService {
    private final WareHouseStockRepository wareHouseStockRepository;
    private final ProductRepository productRepository;
    private final WareHouseRepository wareHouseRepository;
    public WareHouseStockService(WareHouseStockRepository wareHouseStockRepository, ProductRepository productRepository, WareHouseRepository wareHouseRepository){
        this.wareHouseStockRepository = wareHouseStockRepository;
        this.productRepository = productRepository;
        this.wareHouseRepository = wareHouseRepository;
    }
    @Transactional
//    Create
    public WareHouseStock tambahWareHouseStock(WareHouseStockCreate wareHouseStockCreate){
        Product product = productRepository.findById(wareHouseStockCreate.getProductId())
                .orElseThrow(()-> new RuntimeException("Product Tidak Di Temukan"));
        WareHouse wareHouse = wareHouseRepository.findById(wareHouseStockCreate.getWareHouseId())
                .orElseThrow(()-> new RuntimeException("WareHouse Id Tidak Di Temukan"));

        WareHouseStock wareHouseStock = new WareHouseStock();
        wareHouseStock.setStock(wareHouseStockCreate.getStock());
        wareHouseStock.setProduct(product);
        wareHouseStock.setWareHouse(wareHouse);

        wareHouseStock = wareHouseStockRepository.save(wareHouseStock);
        return wareHouseStock;
    }
//    Read All
    public List <WareHouseStock> getWareHouseStock(){
        return wareHouseStockRepository.findAll();
    }
//    Read ById
    public  WareHouseStock getWareHouseStockById(Long id){
        return wareHouseStockRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Data Tidak Di Temukan"));
    }
    @Transactional
//    Update
    public WareHouseStock updateWareHouseStock(Long id, WareHouseStockUpdate wareHouseStockUpdate){
        WareHouseStock wareHouseStock = wareHouseStockRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("WareHouseStock Dengan Id :" + id + "Tidak Di Temukan"));
        wareHouseStock.setStock(wareHouseStockUpdate.getStock());
        return wareHouseStock;
    }
//    Delete
    public void deleteWareHouseStock(Long id){
        wareHouseStockRepository.deleteById(id);
    }

    // Low Stock
    public List<WareHouseStock> getLowStock(){
        return wareHouseStockRepository.findLowStock();
    }
//  Stock Total
    public Integer getStockSummaryBySku(String sku){
        Product product = productRepository.findBySkuCode(sku)
                .orElseThrow(() -> new RuntimeException("Product Tidak Ditemukan"));

        if(!product.getIsActive()){
            throw new RuntimeException("Product Sudah Tidak Aktif");
        }

        // Ambil semua
        List<WareHouseStock> stocks = wareHouseStockRepository.findByProduct(product);
        return stocks.stream()
                .mapToInt(WareHouseStock::getStock)
                .sum();
    }
}
