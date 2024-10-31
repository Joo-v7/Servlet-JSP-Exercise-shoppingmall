package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Objects;

@RequestMapping(method=RequestMapping.Method.GET, value="/admin.do")
public class adminController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);

            User user = (User) session.getAttribute("user");
            if (Objects.isNull(user) || user.getUserAuth() != User.Auth.ROLE_ADMIN) {
                return "shop/admin/warning";
            } else {
                return "shop/admin/admin_main";
            }

    }
}
