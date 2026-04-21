package com.lintang.miniproject.Service;

import com.lintang.miniproject.Model.Product;
import com.lintang.miniproject.Model.StockMutation;
import com.lintang.miniproject.Model.WareHouse;
import com.lintang.miniproject.Model.WareHouseStock;
import com.lintang.miniproject.Repository.ProductRepository;
import com.lintang.miniproject.Repository.StockMutationRepository;
import com.lintang.miniproject.Repository.WareHouseRepository;
import com.lintang.miniproject.Repository.WareHouseStockRepository;
import com.lintang.miniproject.Request.StockMutationRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMutationService {
    private final StockMutationRepository stockMutationRepository;
    private final ProductRepository productRepository;
    private final WareHouseRepository wareHouseRepository;
    private final WareHouseStockRepository wareHouseStockRepository;

    public StockMutationService(StockMutationRepository stockMutationRepository, ProductRepository productRepository, WareHouseRepository wareHouseRepository, WareHouseStockRepository wareHouseStockRepository){
        this.stockMutationRepository = stockMutationRepository;
        this.productRepository = productRepository;
        this.wareHouseRepository = wareHouseRepository;
        this.wareHouseStockRepository = wareHouseStockRepository;
    }

    @Transactional
    public StockMutation tambahStockMutation(StockMutationRequest request){
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Tidak Ditemukan"));

        // Type IN - barang masuk dari luar
        if(request.getType().equals("IN")){
            WareHouse toWareHouse = wareHouseRepository.findById(request.getToWareHouseId())
                    .orElseThrow(() -> new RuntimeException("WareHouse Tidak Ditemukan"));

            // Update stok di warehouse tujuan
            WareHouseStock wareHouseStock = wareHouseStockRepository
                    .findByProductAndWareHouse(product, toWareHouse)
                    .orElse(new WareHouseStock());

            wareHouseStock.setProduct(product);
            wareHouseStock.setWareHouse(toWareHouse);
            wareHouseStock.setStock(wareHouseStock.getStock() == null ?
                    request.getQuantity() :
                    wareHouseStock.getStock() + request.getQuantity());
            wareHouseStockRepository.save(wareHouseStock);

            StockMutation stockMutation = new StockMutation();
            stockMutation.setProduct(product);
            stockMutation.setFromWareHouse(nullz);
            stockMutation.setToWareHouse(toWareHouse);
            stockMutation.setQuantity(request.getQuantity());
            stockMutation.setType("IN");
            stockMutation = stockMutationRepository.save(stockMutation);
            return stockMutation;
        }

        //  Transfer
        else if(request.getType().equals("Transfer")){
            WareHouse fromWareHouse = wareHouseRepository.findById(request.getFromWarehouseId())
                    .orElseThrow(() -> new RuntimeException("From WareHouse Tidak Ditemukan"));

            WareHouse toWareHouse = wareHouseRepository.findById(request.getToWareHouseId())
                    .orElseThrow(() -> new RuntimeException("To WareHouse Tidak Ditemukan"));

            // Cek stok di gudang asal cukup atau tidak
            WareHouseStock fromStock = wareHouseStockRepository
                    .findByProductAndWareHouse(product, fromWareHouse)
                    .orElseThrow(() -> new RuntimeException("Stok Tidak Ditemukan Di Gudang Asal"));

            if(fromStock.getStock() < request.getQuantity()){
                throw new RuntimeException("Stok Di Gudang Asal Tidak Cukup");
            }

            // Kurangi stok
            fromStock.setStock(fromStock.getStock() - request.getQuantity());
            wareHouseStockRepository.save(fromStock);

            // Tambah stok
            WareHouseStock toStock = wareHouseStockRepository
                    .findByProductAndWareHouse(product, toWareHouse)
                    .orElse(new WareHouseStock());

            toStock.setProduct(product);
            toStock.setWareHouse(toWareHouse);
            toStock.setStock(toStock.getStock() == null ?
                    request.getQuantity() :
                    toStock.getStock() + request.getQuantity());
            wareHouseStockRepository.save(toStock);

            StockMutation stockMutation = new StockMutation();
            stockMutation.setProduct(product);
            stockMutation.setFromWareHouse(fromWareHouse);
            stockMutation.setToWareHouse(toWareHouse);
            stockMutation.setQuantity(request.getQuantity());
            stockMutation.setType("TRANSFER");
            stockMutation = stockMutationRepository.save(stockMutation);
            return stockMutation;
        }
        throw new RuntimeException("Type Tidak Valid, Gunakan IN atau TRANSFER");
    }

    //  Get All
    public List<StockMutation> getAllStockMutation(){
        return stockMutationRepository.findAll();
    }
}