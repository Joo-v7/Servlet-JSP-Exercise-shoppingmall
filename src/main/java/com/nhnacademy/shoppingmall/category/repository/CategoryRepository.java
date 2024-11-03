package com.nhnacademy.shoppingmall.category.repository;

import com.nhnacademy.shoppingmall.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(int categoryId);
    Optional<Category> findByName(String categoryName);
    int save(Category category);
    int delete(int categoryId);
    int update(Category category);
    List<Category> findAll();
    List<Category> findAllByCategoryId(int categoryId);


}
