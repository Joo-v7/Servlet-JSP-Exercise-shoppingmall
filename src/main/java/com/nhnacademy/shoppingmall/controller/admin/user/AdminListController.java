package com.nhnacademy.shoppingmall.controller.admin.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RequestMapping(method=RequestMapping.Method.GET, value="/admin/user/list/admin.do")
public class AdminListController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String authAdmin = String.valueOf(User.Auth.ROLE_ADMIN);
//        String authUser = String.valueOf(User.Auth.ROLE_USER);

        List<User> adminList= userService.getUserList(authAdmin);
//        List<User> userList = userService.getUserList(authUser);

        req.setAttribute("adminList", adminList);
//        req.setAttribute("userList", userList);

        return "shop/admin/user/admin_list";
    }
}
