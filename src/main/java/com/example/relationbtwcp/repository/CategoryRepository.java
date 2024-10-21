package com.example.relationbtwcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relationbtwcp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
