package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import java.util.Set;

@Slf4j
@HandlesTypes(
        value = {
                BaseController.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ControllerFactory controllerFactory = new ControllerFactory();
        log.debug("webappinitializer: controller factory initializer start");
        controllerFactory.initialize(c,ctx);
        log.debug("webappinitializer: controller factory initializer end");

//        어차피 iniitialize에서 servletcontext.setAttribute에서 추가해서 필요없음
//        ctx.setAttribute("controllerFactory", controllerFactory);
//        log.debug("controllerFactory 생성 및 초기화");

    }
}