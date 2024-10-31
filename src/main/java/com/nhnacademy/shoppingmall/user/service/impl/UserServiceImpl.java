package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        if(Objects.isNull(userId)){
            throw new IllegalArgumentException("userId is null");
        }

            Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if(Objects.isNull(user)){
            throw new IllegalArgumentException("User must not be null");
        }
        if(userRepository.countByUserId(user.getUserId())>0){
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if(Objects.isNull(user)){
            throw new IllegalArgumentException("User must not be null");
        }
        if(userRepository.countByUserId(user.getUserId())>1){
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.update(user);

    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if(Objects.isNull(userId)){
            throw new IllegalArgumentException("userId is null");
        }
        if(userRepository.countByUserId(userId)>1){
            throw new UserAlreadyExistsException(userId);
        }
        userRepository.deleteByUserId(userId);
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        if(Objects.isNull(userId) || Objects.isNull(userPassword)){
            throw new IllegalArgumentException("userId or userPassword is null");
        }
//        if(userRepository.countByUserId(userId)<1){
//            throw new UserNotFoundException(userId);
//        }
        try {
            Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(userId, userPassword);
            if(userOptional.isEmpty()){
                log.debug("user가 비어있음");
                throw new UserNotFoundException(userId);
            }
            userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
            return userOptional.orElse(null);
        }catch(Exception e){
            throw new UserNotFoundException(userId);
        }
    }

}
