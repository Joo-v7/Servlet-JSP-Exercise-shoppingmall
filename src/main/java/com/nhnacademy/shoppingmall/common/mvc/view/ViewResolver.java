package com.nhnacademy.shoppingmall.common.mvc.view;

import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ViewResolver {

    public static final String DEFAULT_PREFIX="/WEB-INF/views/";
    public static final String DEFAULT_POSTFIX=".jsp";
    public static final String REDIRECT_PREFIX="redirect:";
    public static final String DEFAULT_SHOP_LAYOUT="/WEB-INF/views/layout/shop.jsp";
    public static final String DEFAULT_ADMIN_LAYOUT="/WEB-INF/views/layout/admin.jsp";
    public static final String LAYOUT_CONTENT_HOLDER = "layout_content_holder";
    public static final String USER_SHOP_LAYOUT = "/WEB-INF/views/layout/user_shop.jsp";

    private final String prefix;
    private final String postfix;

    public ViewResolver(){
        this(DEFAULT_PREFIX,DEFAULT_POSTFIX);
    }
    public ViewResolver(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public  String getPath(String viewName){
        //todo#6-1  postfix+viewNAme+postfix 반환합니다.
        String result;
        if(viewName.startsWith("/")){
            result = prefix+viewName.trim().substring(1)+postfix;
            log.debug("result:{}", result);
        }else {
            result = prefix + viewName + postfix;
        }
        return result;
    }

    public boolean isRedirect(String viewName){
        //todo#6-2 REDIRECT_PREFIX가 포함되어 있는지 체크합니다.
        boolean result = viewName.toLowerCase().contains(REDIRECT_PREFIX);
        return result;
    }

    public String getRedirectUrl(String viewName){
        //todo#6-3 REDIRECT_PREFIX를 제외한 url을 반환합니다.
        String result = viewName.toLowerCase().replace(REDIRECT_PREFIX, "").trim();
        return result;
    }

    public String getLayOut(String viewName){

        /*todo#6-4 viewName에
           /admin/경로가 포함되었다면 DEFAULT_ADMIN_LAYOUT 반환합니다.
           /admin/경로가 포함되어 있지않다면 DEFAULT_SHOP_LAYOUT 반환합니다.
        */
        String result = viewName.contains("/admin/") ? DEFAULT_ADMIN_LAYOUT : DEFAULT_SHOP_LAYOUT;

        return result;
    }
}
