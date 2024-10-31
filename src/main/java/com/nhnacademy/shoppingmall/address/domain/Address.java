package com.nhnacademy.shoppingmall.address.domain;

import java.util.Objects;

public class Address {

    private String addressId;
    private String address;
    private String userId;

    public Address(String address, String userId) {
        this.addressId = "";
        this.address = address;
        this.userId = userId;
    }

    public Address(String addressId, String address, String userId) {
        this.addressId = addressId;
        this.address = address;
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) && Objects.equals(userId, address1.userId);
    }

    @Override
    public int hashCode() {return Objects.hash(address, userId);}

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' + ", userId='" + userId + '\'' + '}';
    }
}
