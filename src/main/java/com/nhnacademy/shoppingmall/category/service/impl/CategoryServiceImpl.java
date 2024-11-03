package com.nhnacademy.shoppingmall.category.service.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(int categoryId) {
        if(categoryId<0){
            throw new IllegalArgumentException("Category id is wrong");
        }
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }
        return category.get();
    }

    @Override
    public Category getCategory(String categoryName) {
        if(categoryName.isEmpty()){
            throw new IllegalArgumentException("categoryName is empty");
        }
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }
        return category.get();
    }

    @Override
    public void saveCategory(Category category){
        if(Objects.isNull(category)){
            throw new IllegalArgumentException("category is null");
        }
        int result = categoryRepository.save(category);
        if(result < 1) {
            throw new RuntimeException("Category not saved");
        }
    }

    @Override
    public void updateCategory(Category category){
        if(Objects.isNull(category)){
            throw new IllegalArgumentException("category is null");
        }
        int result = categoryRepository.update(category);
        if(result < 1) {
            throw new RuntimeException("Category not updated");
        }
    }

    @Override
    public void deleteCategory(int categoryId){
        if(categoryId<0){
            throw new IllegalArgumentException("Category id is wrong");
        }
        int result = categoryRepository.delete(categoryId);
        if(result < 1) {
            throw new RuntimeException("Category not deleted");
        }
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategoriesByCategoryId(int categoryId){
        if(categoryId<0){
            throw new IllegalArgumentException("Category id is wrong");
        }
        return categoryRepository.findAllByCategoryId(categoryId);
    }


}
