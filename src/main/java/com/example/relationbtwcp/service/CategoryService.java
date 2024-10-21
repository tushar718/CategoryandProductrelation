package com.example.relationbtwcp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.relationbtwcp.entity.Category;
import com.example.relationbtwcp.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(int page, int size) {
    	System.out.println("CategoryService.getAllCategories() || page:" +page +" || size:" +size );
        return categoryRepository.findAll(); 
    }

    public Category addCategory(Category category) {
    	System.out.println("CategoryService.addCategory()|| category:" +category);
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
    	System.out.println("CategoryService.getCategoryById() || id:" +id);
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Long id, Category category) {
    	System.out.println("CategoryService.updateCategory() || id:" +id +" || category:" +category);
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            updatedCategory.setDescription(category.getDescription());
            return categoryRepository.save(updatedCategory);
        }
        return null;
    }

    public boolean deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        System.out.println("CategoryService.deleteCategory() || id:" +id);
        return true;
    }
}