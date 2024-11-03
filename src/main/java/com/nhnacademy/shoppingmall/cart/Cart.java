package com.nhnacademy.shoppingmall.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart implements Serializable {

    private final List<CartItem> cartItems;

    public Cart() {
        cartItems = Collections.synchronizedList(new ArrayList<>());
    }

    public void tryAddItem(CartItem cartItem) throws ProductNotFoundException{
        if(cartItems.contains(cartItem)){
            throw new ProductNotFoundException(cartItem.getProductId());
        }else{
            cartItems.add(cartItem);
        }
    }

    public void removeItem(CartItem cartItem) throws ProductNotFoundException{
        if(!cartItems.contains(cartItem)){
            throw new ProductNotFoundException(cartItem.getProductId());
        }else{
            cartItems.remove(cartItem);
        }
    }
}
