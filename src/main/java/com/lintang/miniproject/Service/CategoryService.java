package com.lintang.miniproject.Service;

import com.lintang.miniproject.Model.Category;
import com.lintang.miniproject.Repository.CategoryRepository;
import com.lintang.miniproject.Request.CategoryRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Transactional
//    Create Category
    public Category createCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category = categoryRepository.save(category);
        return category;
    }
//    Get All Category
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
//    Get ById
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category Dengan Id : " + id + " Tidak Di Temukan"));
    }
    @Transactional
//    Update Category
    public Category updateCategory(Long id,  CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category Dengan Id: " + id + " Tidak Di Temukan"));

        category.setName(categoryRequest.getName());
        category = categoryRepository.save(category);
        return category;
    }

//    Delete
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
