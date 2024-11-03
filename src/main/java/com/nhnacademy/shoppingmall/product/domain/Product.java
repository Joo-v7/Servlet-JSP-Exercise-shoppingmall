package com.nhnacademy.shoppingmall.product.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private final int productId;
    private final int categoryId;
    private String productName;
    private int productPrice;
    private int quantity;
    private LocalDateTime registration;
    private String description;
    private String imageUrl;

    public Product(int productId, int categoryId, String productName, int productPrice,
                   int quantity, LocalDateTime registration, String description, String imageUrl) {
        if(productId < 0){
            throw new IllegalArgumentException("Invalid product parameter");
        }
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.description = description;
        this.productName = productName;
        this.registration = registration;
        this.imageUrl = imageUrl;
    }

    public Product(int productId, int categoryId, String productName, int productPrice,
                   int quantity, LocalDateTime registration, String description) {
        if(productId < 0){
            throw new IllegalArgumentException("Invalid product parameter");
        }
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.description = description;
        this.productName = productName;
        this.registration = registration;
    }
    public String getImageUrl(){
        return imageUrl;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }
    public String getProductName(){
        return productName;
    }
    public int getProductId() {
        return productId;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("quantity < 0");
        }
        this.quantity = quantity;
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if( o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productPrice == product.productPrice && quantity == product.quantity && categoryId == product.categoryId
                && Objects.equals(productName, product.productName) && Objects.equals(description, product.description) && Objects.equals(registration, product.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productPrice, quantity, categoryId, productName, description, registration);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName=" + productName +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", category=" + categoryId +
                ", description='" + description +
                ", registration=" + registration + '}';
    }
}
