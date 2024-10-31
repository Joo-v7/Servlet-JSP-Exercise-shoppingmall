package com.nhnacademy.shoppingmall.controller.mypage.myInfo;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value="/mypage/myinfo/view.do")
public class MyInfoViewController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) ) {
            return "shop/login/login_form";
        }else {
            User user = (User) session.getAttribute("user");
            if(Objects.isNull(user)) {
                return "shop/login/login_form";
            }else {
                user = userService.doLogin(user.getUserId(), user.getUserPassword());
                req.setAttribute("user", user);
                log.debug("mypage user info:{}", user.toString());
                return "shop/mypage/myinfo/view";
            }

        }
    }
}



