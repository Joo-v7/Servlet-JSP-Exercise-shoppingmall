package com.nhnacademy.shoppingmall.controller.auth;

import com.mysql.cj.Session;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/login.do")
public class LoginController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-1 session이 존재하고 로그인이 되어 있다면 redirect:/index.do 반환 합니다.
        HttpSession session = req.getSession(false);
        if(Objects.nonNull(session) && session.getAttribute("user") != null) {
            //try {
                return "shop/main/index";
//                resp.sendRedirect("/index.do");
//            }catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
        return "shop/login/login_form";
    }
}
