package com.lintang.miniproject.Controller;

import com.lintang.miniproject.Model.Product;
import com.lintang.miniproject.Request.ProductRequestCreate;
import com.lintang.miniproject.Request.ProductRequestUpdate;
import com.lintang.miniproject.Response.WebResponse;
import com.lintang.miniproject.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
//    Create
    @PostMapping
    public WebResponse<Product> addProduct(@Valid @RequestBody ProductRequestCreate productRequestCreate){
        return WebResponse.<Product>builder()
                .status("Berhasil")
                .message("Product Berhasil Di Tambahkan")
                .data(productService.tambahProduct(productRequestCreate))
                .build();
    }

//    Get
    @GetMapping
    public List <Product> semuaDataProduct(){
        return productService.getProduct();
    }

//    Read ById
    @GetMapping("/{id}")
    public WebResponse <Product> dataProductById(@PathVariable Long id){
        Product product = productService.getProductByid(id);
        return new WebResponse<>("Done","Data Product : ", product);
    }

//    Get ByIsActive
    @GetMapping("/status/{isActive}")
    public  WebResponse<List<Product>> getByIsActive(@PathVariable Boolean isActive){
        List <Product> product = productService.getProductByIsActive(isActive);
        return new WebResponse<>("Done", "Berhasil Di Get ", product);
    }

//    Update /api/product/{id}
    @PutMapping("/{id}")
    public WebResponse <Product> updateProduct (@PathVariable Long id,@Valid @RequestBody ProductRequestUpdate productRequestUpdate){
        Product product = productService.updateProduct(id, productRequestUpdate);
        return new WebResponse<>("Done","Product Berhasil Di Update", product);
    }

//    Delete
    @GetMapping("/remove/{id}")
    public WebResponse<Product> removeProduct (@PathVariable Long id) {
        return WebResponse.<Product>builder()
                .status("Done")
                .message("Barang sudah di remove")
                .data(productService.deleteProduct(id))
                .build();
    }

    @GetMapping("/restore/{id}")
    public WebResponse<Product> restoreProduct (@PathVariable Long id) {
        return WebResponse.<Product>builder()
                .status("Done")
                .message("Barang sudah di Restore")
                .data(productService.restoreProduct(id))
                .build();
    }


}
