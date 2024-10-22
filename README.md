# Category-Product API with Pagination
## Table of Contents
1. [Introduction](#introduction): This project provides an API for managing categories and products with a one-to-many relationship. It includes pagination to handle large sets of products efficiently.
2. [Technologies Used](#technologies-used): Java, JPA, SpringBoot, MySql. 
3. [Database Structure](#database-structure): Describes the structure of the database, including the tables (or schemas) and the one-to-many relationship between Category and Product.
   - [Category Table](#category-table): Lists the fields and types for the Category entity.
   - [Product Table](#product-table): Lists the fields and types for the Product entity.
   - [Relationship](#relationship): Explanation of the one-to-many relationship between Category and Product.
4. [API Endpoints](#api-endpoints): Detailed list of the available API endpoints for managing Categories and Products.
   - [Categories](#categories): Endpoints related to categories (CRUD operations).
   - [Products](#products): Endpoints related to products, including pagination.
   - [Example Request](#example-request): GET /api/categories/products?page=2&limit=5
5. [Pagination](#pagination): Explanation of how pagination works in the API, including parameters like page and limit.
   - [Example Logic for Pagination](#example-logic-for-pagination): GET /categories/{categoryId}/products?page={page}&limit={limit}


