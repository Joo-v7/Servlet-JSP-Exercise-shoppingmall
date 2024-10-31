package com.nhnacademy.shoppingmall.common.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns={"/mypage/"})
@Slf4j
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        if(req.getServletPath().toLowerCase().contains("mypage")){
            log.debug("mypage filter");
            if(req.getSession(false) != null){ // 로그인 되어 있으면
                chain.doFilter(req, res);
            }else { // 로그인 안 되어 있으면
                res.sendRedirect("/login.do");
            }
        }else {
            log.debug("logincheckfilter - 다음 필터로 넘김");
            chain.doFilter(req, res);
        }
    }
}