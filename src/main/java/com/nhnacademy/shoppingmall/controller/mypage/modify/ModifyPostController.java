package com.nhnacademy.shoppingmall.controller.mypage.modify;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RequestMapping(method=RequestMapping.Method.POST, value="/mypage/mymodify/modifyAction.do")
public class ModifyPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("user_id");
        String name = req.getParameter("user_name");
        String pw = req.getParameter("user_password");
        String birth = req.getParameter("user_birth");
        String auth = req.getParameter("user_auth");
        int point = Integer.parseInt(req.getParameter("user_point"));
        LocalDateTime createdAt = LocalDateTime.parse(req.getParameter("createdAt"));
        LocalDateTime latestLoginAt = LocalDateTime.parse(req.getParameter("latestLoginAt"));


            if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(pw) || Objects.isNull(birth) || Objects.isNull(auth)
                    || id.isEmpty() || name.isEmpty() || pw.isEmpty() || birth.isEmpty() || auth.isEmpty()) {
                log.debug("회원 정보 수정시 input이 잘못됨");
                return "error/alertError";
            }else {
                User user = new User(id, name, pw, birth, User.Auth.valueOf(auth), point, createdAt, latestLoginAt);
                userService.updateUser(user);
                try {
                    resp.sendRedirect("/mypage/myinfo/view.do");
                }catch(IOException e){
                    return "error/alertError";
                }
            }
        return "error/alertError";
    }
}
