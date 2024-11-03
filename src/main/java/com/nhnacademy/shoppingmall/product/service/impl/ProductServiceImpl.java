package com.nhnacademy.shoppingmall.product.service.impl;


import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.cart.ProductNotFoundException;

import java.util.List;
import java.util.Objects;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId){
        if(productId < 0 || !productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }else{
            return productRepository.findById(productId).get();
        }
    }

    @Override
    public void saveProduct(Product product){
        if(Objects.isNull(product)){
            throw new IllegalArgumentException("Product must not be null");
        }else{
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(int productId){
        if(productId < 0 || !productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }else{
            productRepository.deleteById(productId);
        }
    }

    @Override
    public int getTotalCount(){ // 등록된 총 상품들의 수
        return productRepository.count();
    }

    @Override
    public void updateProduct(Product product){
        if(Objects.isNull(product)){
            throw new IllegalArgumentException("Product must not be null");
        }else{
            productRepository.updateProduct(product);
        }
    }

    @Override
    public int pickProduct(int productId, int quantity) { // 제품 카트에 싣으면 quantity 차감
        if(productId < 0 || quantity < 0 || !productRepository.existsById(productId)){
            throw new IllegalArgumentException("parameter something wrong");
        }else{
            Product product = productRepository.findById(productId).get();
            product.setQuantity(product.getQuantity() - quantity);
            return product.getQuantity();
        }
    }

    @Override
    public int returnProduct(int productId, int quantity) { // 카트에서 취소하면 다시 복구
        if(productId < 0 || quantity < 0 || !productRepository.existsById(productId)){
            throw new IllegalArgumentException("parameter something wrong");
        }else{
            Product product = productRepository.findById(productId).get();
            product.setQuantity(product.getQuantity() + quantity);
            return product.getQuantity();
        }
    }

    @Override
    public List<Product> AllProducts(){
        return productRepository.findAll();
    }

}
