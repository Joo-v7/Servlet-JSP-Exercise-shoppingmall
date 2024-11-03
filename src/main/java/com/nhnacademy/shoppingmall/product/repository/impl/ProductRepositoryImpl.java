package com.nhnacademy.shoppingmall.product.repository.impl;


import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into product values(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, product.getCategoryId());
            statement.setString(3, product.getProductName());
            statement.setInt(4, product.getProductPrice());
            statement.setInt(5, product.getQuantity());
            statement.setTimestamp(6, Timestamp.valueOf(product.getRegistration()));
            statement.setString(7, product.getDescription());

            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(int productId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from product where product_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getInt("category_id"),
                    rs.getString("product_name"),
                    rs.getInt("product_price"),
                    rs.getInt("product_quantity"),
                    rs.getTimestamp("product_registration").toLocalDateTime(),
                    rs.getString("description")
            );

            return Optional.of(product);

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int productId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from product where product_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, productId);
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(int productId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product where product_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            int result=0;
            if(rs.next()){
                result = rs.getInt(1);
            }
            return result==0 ? false:true;

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int count(){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            int result=0;
            if(rs.next()){
                result = rs.getInt(1);
            }
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countQuantityById(int productId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select product_quantity from product where product_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            int result=0;
            if(rs.next()){
                result = rs.getInt(1);
            }
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateProduct(Product product){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update product set category_id = ?, product_name = ?, product_price = ?, product_quantity = ?, product_registration = ?, description = ? where product_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getProductPrice());
            statement.setInt(4, product.getQuantity());
            statement.setTimestamp(5, Timestamp.valueOf(product.getRegistration()));
            statement.setString(6, product.getDescription());
            statement.setInt(7, product.getProductId());
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll(){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from product";
        List<Product> products = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getTimestamp("product_registration").toLocalDateTime(),
                        rs.getString("description")
                );
                products.add(product);
            }
            return products;

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
