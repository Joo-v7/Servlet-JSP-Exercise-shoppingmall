package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.mysql.cj.Session;
import com.nhnacademy.shoppingmall.common.mvc.exception.PageNotFoundException;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;

import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

@Slf4j
@WebServlet(name = "frontServlet",urlPatterns = {"*.do"})
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        //todo#7-1 controllerFactory를 초기화 합니다.
        ServletContext ctx = getServletContext();
        controllerFactory = (ControllerFactory) ctx.getAttribute(ControllerFactory.CONTEXT_CONTROLLER_FACTORY_NAME);

        //todo#7-2 viewResolver를 초기화 합니다.
        viewResolver = new ViewResolver();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        try{
            //todo#7-3 Connection pool로 부터 connection 할당 받습니다. connection은 Thread 내에서 공유됩니다.
            DbConnectionThreadLocal.initialize();

            BaseController baseController = (BaseController) controllerFactory.getController(req);
            String viewName = baseController.execute(req,resp);

            if(viewResolver.isRedirect(viewName)){
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                log.debug("redirectUrl:{}",redirectUrl);
                //todo#7-6 redirect: 로 시작하면  해당 url로 redirect 합니다.
                resp.sendRedirect(redirectUrl);

            }else {
                String layout = viewResolver.getLayOut(viewName);

                // login하면 layout 변경.
                HttpSession session = req.getSession(false);
                if(Objects.nonNull(session) && session.getAttribute("user")!=null){
                    layout = viewResolver.USER_SHOP_LAYOUT;
                }

                log.debug("layout222:{}",layout);
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp); // layout include
            }
        }catch (Exception e){
            log.error("error:{}",e);
            DbConnectionThreadLocal.setSqlError(true);
            //todo#7-5 예외가 발생하면 해당 예외에 대해서 적절한 처리를 합니다.
            throw new PageNotFoundException(e.getMessage());

        }finally {
            //todo#7-4 connection을 반납합니다.
            DbConnectionThreadLocal.reset();
        }
    }


}
