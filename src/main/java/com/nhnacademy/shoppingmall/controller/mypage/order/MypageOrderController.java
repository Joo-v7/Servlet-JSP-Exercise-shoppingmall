package com.nhnacademy.shoppingmall.controller.mypage.order;

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
@RequestMapping(method = RequestMapping.Method.GET, value="/mypage/myorder/order.do")
public class MypageOrderController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || session.getAttribute("user") == null) {
            return "shop/login/login_form";
        }else {
            User user = (User) session.getAttribute("user");

            if (Objects.isNull(user)) {
                log.warn("user is null");
                return "shop/login/login_form";
            }

            req.setAttribute("user", user);
            log.debug("mypage user info:{}", user.toString());

            return "shop/mypage/myorder/order";
        }
    }
}



