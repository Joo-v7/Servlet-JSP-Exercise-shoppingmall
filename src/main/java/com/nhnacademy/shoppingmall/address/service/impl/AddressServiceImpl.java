package com.nhnacademy.shoppingmall.address.service.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.service.AddressService;

import java.util.List;
import java.util.Objects;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddress(String userId){
        if(Objects.isNull(userId)){
            throw new IllegalArgumentException("userId is null");
        }

        List<Address> address = addressRepository.findByUserId(userId);

        return address.isEmpty() ? null : address;
    }

    public void saveAddress(Address address){
        if(Objects.isNull(address)){
            throw new IllegalArgumentException("address is null");
        }
        addressRepository.save(address);
    }

    public void updateAddress(Address address) {
        if(Objects.isNull(address)){
            throw new IllegalArgumentException("address is null");
        }
        addressRepository.update(address);
    }

    public void deleteAddress(String address, String userId) {
        if(Objects.isNull(address)){
            throw new IllegalArgumentException("addressId is null");
        }

        addressRepository.delete(address, userId);
    }
}
