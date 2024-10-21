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

import com.example.relationbtwcp.entity.Product;
import com.example.relationbtwcp.response.ProductResponse;
import com.example.relationbtwcp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(productService.getAllProducts(page, size).getContent());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        ProductResponse response = new ProductResponse();
        response.setMessage("Product successfully added.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setProduct(newProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        ProductResponse response = new ProductResponse();
        if (product.isPresent()) {
            response.setMessage("Product found.");
            response.setStatus(HttpStatus.OK.value());
            response.setProduct(product.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Product not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        ProductResponse response = new ProductResponse();
        if (updatedProduct != null) {
            response.setMessage("Product successfully updated.");
            response.setStatus(HttpStatus.OK.value());
            response.setProduct(updatedProduct);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Product not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            ProductResponse response = new ProductResponse();
            response.setMessage("Product successfully deleted.");
            response.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ProductResponse response = new ProductResponse();
            response.setMessage("Product not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}