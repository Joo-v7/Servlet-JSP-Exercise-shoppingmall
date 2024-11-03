package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    int save(Product product);
    Optional<Product> findById(int productId);
    int deleteById(int productId);
    boolean existsById(int productId);
    int count();
    int countQuantityById(int productId);
    int updateProduct(Product product);
    List<Product> findAll();
}
