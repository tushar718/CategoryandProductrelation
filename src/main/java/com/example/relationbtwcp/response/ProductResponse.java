package com.example.relationbtwcp.response;

import com.example.relationbtwcp.entity.Product;

public class ProductResponse {
    private String message;
    private int status;
    private Product product;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}