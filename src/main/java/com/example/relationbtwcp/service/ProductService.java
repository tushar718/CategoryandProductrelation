package com.example.relationbtwcp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.relationbtwcp.entity.Category;
import com.example.relationbtwcp.entity.Product;
import com.example.relationbtwcp.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public Page<Product> getAllProducts(int page, int size) {
    	System.out.println("ProductService.getAllProducts() || page: "+page+ " || size" +size);
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product addProduct(Product product) {
    	Optional<Category> categoryById = categoryService.getCategoryById(product.getCategory().getId());
    	
    	
    	
         Product save = productRepository.save(product);
         save.setCategory(categoryById.get());
         System.out.println("ProductService.addProduct() || product:"+product);
         return save;
    }

    public Optional<Product> getProductById(Long id) {
    	System.out.println("ProductService.getProductById() || id :"+id);
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setCategory(product.getCategory());
            System.out.println("ProductService.updateProduct() || id:"+id +"|| product:"+product);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
        	System.out.println("ProductService.deleteProduct() || id:"+id);
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
