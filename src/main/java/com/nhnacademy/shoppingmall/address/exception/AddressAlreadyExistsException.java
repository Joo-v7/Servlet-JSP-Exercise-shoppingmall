
package com.nhnacademy.shoppingmall.address.exception;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String userId){
        super(String.format("address already exist:%s",userId));
    }
}