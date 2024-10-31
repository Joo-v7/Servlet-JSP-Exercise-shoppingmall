package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns={"/admin/"})
@Slf4j
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        if(req.getServletPath().toLowerCase().contains("admin")) {
            log.debug("admin filter");
            User user = (User) req.getSession().getAttribute("user");
            if(user.getUserAuth() == User.Auth.ROLE_ADMIN) {
                chain.doFilter(req, res);
            }else{
                res.sendError(HttpServletResponse.SC_FORBIDDEN); // 403처리
            }
        }else {
            log.debug("admincheckfilter - 다음 필터로 넘김");
            chain.doFilter(req, res);
        }
    }
}
