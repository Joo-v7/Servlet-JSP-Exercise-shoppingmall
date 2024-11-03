package com.nhnacademy.shoppingmall.category.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Optional<Category> findById(int categoryId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category where category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
                );
                return Optional.of(category);
            }
            return Optional.empty();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Category> findByName(String categoryName){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category where category_name = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, categoryName);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                return Optional.of(category);
            }
            return Optional.empty();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Category category){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into category values(?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getCategoryName());
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int categoryId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from category where category_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, categoryId);
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update category set category_name = ? where category_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryId());
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAll(){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category";
        List<Category> categorys = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                categorys.add(category);
            }
            return categorys;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAllByCategoryId(int categoryId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category where category_id = ?";

        List<Category> categorys = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                categorys.add(category);
            }
            return categorys;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
