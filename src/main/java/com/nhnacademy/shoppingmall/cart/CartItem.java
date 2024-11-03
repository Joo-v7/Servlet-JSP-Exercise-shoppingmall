package com.nhnacademy.shoppingmall.cart;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {

    private final int productId;
    private final int quantity;

    public CartItem(int productId, int quantity) {
        if(productId<0 || quantity<0) {
            throw new IllegalArgumentException("Invalid product");
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return productId == cartItem.productId && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }
}
