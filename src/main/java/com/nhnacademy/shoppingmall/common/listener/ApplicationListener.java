package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDateTime;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        log.debug("ApplicationListener start");
        DbConnectionThreadLocal.initialize();

        User adminUser = new User("admin", "admin", "12345", "19981218", User.Auth.ROLE_ADMIN, 1000000, LocalDateTime.now(), null);
        User normalUser = new User("user", "user", "12345", "20241029", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null);

        UserRepository userRepository = new UserRepositoryImpl();
        if(userRepository.findByUserIdAndUserPassword(adminUser.getUserId(), adminUser.getUserPassword()).isEmpty()){
            userService.saveUser(adminUser);
        }else if(userRepository.findByUserIdAndUserPassword(normalUser.getUserId(), normalUser.getUserPassword()).isEmpty()){
            userService.saveUser(normalUser);
        }
        DbConnectionThreadLocal.reset();
    }
}
