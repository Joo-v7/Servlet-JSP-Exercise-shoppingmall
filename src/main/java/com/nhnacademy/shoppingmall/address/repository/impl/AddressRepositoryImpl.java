package com.nhnacademy.shoppingmall.address.repository.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AddressRepositoryImpl implements AddressRepository {

    @Override
    public List<Address> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "select address_id, address, user_id from address where user_id=?";

        List<Address> addressList = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String addressId = String.valueOf(rs.getInt("address_id"));
                String address = rs.getString("address");
                String userId1 = rs.getString("user_id");
                Address address1 = new Address(addressId, address, userId1);
                addressList.add(address1);
            }
            return addressList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public int save(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into address (address, user_id) values (?, ?)";

        try(
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setString(1, address.getAddress());
            statement.setString(2, address.getUserId());
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from address where address_id=?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setString(1, address.getAddressId());
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Address address){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update address set address=?, user_id=? where address_id=?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setString(1, address.getAddress());
            statement.setString(2, address.getUserId());
            statement.setInt(3, Integer.parseInt(address.getAddressId()));
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
