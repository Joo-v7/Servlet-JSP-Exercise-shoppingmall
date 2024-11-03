package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    void saveProduct(Product product);
    void deleteProduct(int productId);
    int getTotalCount();
    void updateProduct(Product product);
    int pickProduct(int productId, int quantity); // 제품 카트에 싣으면 quantity 차감
    int returnProduct(int productId, int quantity); // 제품 카트에서 취소하면 quantity 추가
    List<Product> AllProducts();
}
