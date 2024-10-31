package com.nhnacademy.shoppingmall.controller.auth;

import com.mysql.cj.Session;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.transaction.Transactional;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.

        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_password");

        if(Objects.isNull(id) || Objects.isNull(pw) || id.isEmpty() || pw.isEmpty()) {
            throw new IllegalArgumentException();
        }

        try {
            User user = userService.doLogin(id, pw);
            if(Objects.isNull(user)){
                return "shop/login/login_form";
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3600);
                return "shop/main/index";
            }
        }catch(Exception e) {
            throw new UserNotFoundException(id);
        }
    }
}
