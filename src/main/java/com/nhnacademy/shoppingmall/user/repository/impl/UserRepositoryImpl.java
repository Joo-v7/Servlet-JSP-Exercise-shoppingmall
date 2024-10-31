package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.sql.Types.NULL;
import static java.sql.Types.VARCHAR;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */

        /*
        Q: jdbc에서는 ResultSet을 finally로 close() 해줬는데 여기서는 안하는 이유?
        A: Connection을 닫으면 해당 Connection에서 생성된 모든 Statement, ResultSet도 닫힘.

        Q: 그럼 DbConnectionThreadLocal에서 reset()을 불러줘야 하는거 아닌가? 아니면 하나의 connection으로 계속 사용?
        */

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=? and user_password =?";

        log.debug("sql:{}",sql);

        try( PreparedStatement psmt = connection.prepareStatement(sql)
        ) {
            psmt.setString(1, userId);
            psmt.setString(2, userPassword);
            ResultSet rs =  psmt.executeQuery();

            if(rs.next()){
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=?";

        try(
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into users (user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserBirth());
            statement.setString(5, String.valueOf(user.getUserAuth()));
            statement.setInt(6, user.getUserPoint());
            statement.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
//            Objects.nonNull(user.getLatestLoginAt()) ? statement.setTimestamp(8, Timestamp.valueOf(user.getLatestLoginAt())) : statement.setNull(8, null);
            if(Objects.isNull(user.getLatestLoginAt())){
                statement.setNull(8, NULL);
            }else {
                statement.setTimestamp(8, Timestamp.valueOf(user.getLatestLoginAt()));
            }


            int result = statement.executeUpdate();
            return result;

        }catch(SQLIntegrityConstraintViolationException e){
            throw new RuntimeException(e);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from users where user_id=?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setString(1, userId);
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update users set user_name=?, user_password=?, user_birth=?, user_auth=?, user_point=?, created_at=?, latest_login_at=? where user_id=?";
        try(
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserBirth());
            statement.setString(4, String.valueOf(user.getUserAuth()));
            statement.setInt(5, user.getUserPoint());
            statement.setTimestamp(6, Timestamp.valueOf(user.getCreatedAt()));
            if(Objects.isNull(user.getLatestLoginAt())){
                statement.setNull(7, NULL);
            }else {
                statement.setTimestamp(7, Timestamp.valueOf(user.getLatestLoginAt()));
            }
            statement.setString(8, user.getUserId());

            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update users set latest_login_at=? where user_id=?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            statement.setString(2, userId);
            int result = statement.executeUpdate();
            return result;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from users where user_id=?";
        try(
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

}
