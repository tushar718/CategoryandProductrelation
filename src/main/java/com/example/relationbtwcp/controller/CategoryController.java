package com.example.relationbtwcp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.relationbtwcp.entity.Category;
import com.example.relationbtwcp.response.CategoryResponse;
import com.example.relationbtwcp.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam int page, @RequestParam int size) {
    	System.out.println("CategoryController.getAllCategories() || page:"+page+" || size" +size);
        return ResponseEntity.ok(categoryService.getAllCategories(page, size));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.addCategory(category);
        CategoryResponse response = new CategoryResponse();
        response.setMessage("Category successfully added.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setCategory(newCategory);
        System.out.println("CategoryController.addCategory() || category:"+category);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        CategoryResponse response = new CategoryResponse();
        if (category.isPresent()) {
            response.setMessage("Category found.");
            response.setStatus(HttpStatus.OK.value());
            response.setCategory(category.get());
            System.out.println("CategoryController.getCategoryById()|| id:"+id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Category not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        CategoryResponse response = new CategoryResponse();
        if (updatedCategory != null) {
            response.setMessage("Category successfully updated.");
            response.setStatus(HttpStatus.OK.value());
            response.setCategory(updatedCategory);
            System.out.println("CategoryController.updateCategory() || id:"+id+"|| category:"+category);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Category not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        CategoryResponse response = new CategoryResponse();
        response.setMessage("Category successfully deleted.");
        response.setStatus(HttpStatus.NO_CONTENT.value());
        System.out.println("CategoryController.deleteCategory() || id:"+id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}