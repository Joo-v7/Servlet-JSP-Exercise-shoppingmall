package com.nhnacademy.shoppingmall.controller.admin.main;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Objects;

@RequestMapping(method=RequestMapping.Method.GET, value="/admin.do")
public class AdminController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            HttpSession session = req.getSession(false);
            if (Objects.isNull(session)) {
                return "shop/admin/warning";
            } else {
                User user = (User) session.getAttribute("user");
                if (Objects.isNull(user)) {
                    return "shop/admin/warning";
                } else if (user.getUserAuth().equals(User.Auth.ROLE_ADMIN)) {
                    return "shop/admin/main/admin_main";
                }else {
                    return "shop/admin/warning";
                }
            }
        }catch(Exception e) {
            return "shop/admin/warning";
        }

    }
}
