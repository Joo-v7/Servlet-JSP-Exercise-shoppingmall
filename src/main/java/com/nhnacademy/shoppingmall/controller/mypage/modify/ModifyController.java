package com.nhnacademy.shoppingmall.controller.mypage.modify;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value="/mypage/mymodify/modify.do")
public class ModifyController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession httpSession = req.getSession(false);
        if(Objects.isNull(httpSession)) {
            return "error/alertError";
        }
        User user = (User) httpSession.getAttribute("user");
        if(Objects.isNull(user)) {
            return "error/alertError";
        }else {
            req.setAttribute("user", user);
            return "shop/mypage/mymodify/modify";
        }
    }
}



