package com.lintang.miniproject.Service;

import com.lintang.miniproject.Model.Category;
import com.lintang.miniproject.Model.Product;
import com.lintang.miniproject.Repository.CategoryRepository;
import com.lintang.miniproject.Repository.ProductRepository;
import com.lintang.miniproject.Request.ProductRequestCreate;
import com.lintang.miniproject.Request.ProductRequestUpdate;
import com.lintang.miniproject.Response.WebResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
//    Create
    @Transactional
    public Product tambahProduct(ProductRequestCreate productRequestCreate){
        Category category = categoryRepository.findById(productRequestCreate.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Tidak Di Temukan"));

        Product product = new  Product();
        product.setName(productRequestCreate.getName());
        product.setPrice(productRequestCreate.getPrice());
        product.setSkuCode(productRequestCreate.getSku_code());
        product.setCategory(category);
        product = productRepository.save(product);
        return product;
    }
//  Read
    public List <Product> getProduct(){
        return productRepository.findAll();
    }
//    Get Is Active
    public  List <Product> getProductByIsActive(Boolean isActive){
       return productRepository.findByIsActive(isActive);
    }
//  Read ById
    public Product getProductByid(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Dengan Id: " +id + " Tidak Di Temukan"));
    }
//    Update
    @Transactional
    public Product updateProduct(Long id, ProductRequestUpdate productRequestUpdate){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produk Dengan Id" + id + " Tidak Di Temukan"));

        Category category = categoryRepository.findById(productRequestUpdate.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Tidak Di Temukan"));

        product.setName(productRequestUpdate.getName());
        product.setPrice(productRequestUpdate.getPrice());
        product.setSkuCode(productRequestUpdate.getSku_code());
        product.setCategory(category);
        product = productRepository.save(product);
        return product;
    }
//    Delete
    public Product deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        product.setIsActive(false);
        return productRepository.save(product);
    }

    public Product restoreProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        product.setIsActive(true);
        return productRepository.save(product);
    }
}
