package com.nhnacademy.shoppingmall.category.service;

import com.nhnacademy.shoppingmall.category.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryId);
    Category getCategory(String categoryName);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByCategoryId(int categoryId);
}
