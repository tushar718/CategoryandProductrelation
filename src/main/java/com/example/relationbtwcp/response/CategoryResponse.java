package com.example.relationbtwcp.response;

import com.example.relationbtwcp.entity.Category;

public class CategoryResponse {
    private String message;
    private int status;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}