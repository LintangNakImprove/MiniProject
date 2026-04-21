package com.lintang.miniproject.Controller;

import com.lintang.miniproject.Model.Category;
import com.lintang.miniproject.Request.CategoryRequest;
import com.lintang.miniproject.Response.WebResponse;
import com.lintang.miniproject.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")

public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

//    Create
    @PostMapping
    public WebResponse <Category> tambahCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return WebResponse.<Category>builder()
                .status("Berhasil")
                .message("Category Berhasil Di Tambahkan")
                .data(categoryService.createCategory(categoryRequest))
                .build();
    }

//    Get
    @GetMapping
    public List <Category> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("/{id}")
    public WebResponse <Category> getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        return new WebResponse<>("Berhasil", "Data Category : ", category);
    }

//    Update
    @PutMapping("/{id}")
    public WebResponse <Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.updateCategory(id, categoryRequest);
        return new WebResponse<>("Done", "Category Berhasil Di Update",category);
    }

//    Delete
    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category Dengan Id : " + id + " Berhasil Di Hapus";
    }
}
