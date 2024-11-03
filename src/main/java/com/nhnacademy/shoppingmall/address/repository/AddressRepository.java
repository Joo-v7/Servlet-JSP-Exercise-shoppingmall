package com.nhnacademy.shoppingmall.address.repository;

import com.nhnacademy.shoppingmall.address.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findByUserId(String userId);
    int save(Address address);
    int delete(String address, String userId);
    int update(Address address);

}
