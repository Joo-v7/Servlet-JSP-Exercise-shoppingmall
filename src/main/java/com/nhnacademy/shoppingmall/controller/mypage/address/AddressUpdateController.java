package com.nhnacademy.shoppingmall.controller.mypage.address;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequestMapping(method=RequestMapping.Method.POST, value="/mypage/address/update.do")
public class AddressUpdateController implements BaseController {

    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session)){
            return "error/alertError";
        }else {
            User user = (User) session.getAttribute("user");
            if(Objects.isNull(user)){
                return "error/alertError";
            }else {
                String updateAddress = req.getParameter("updateAddress");
                log.debug("updateAddress:{}", updateAddress);
                String addressId = req.getParameter("addressId");
                addressService.updateAddress(
                        new Address(addressId, updateAddress, user.getUserId())
                );
                try {
                    resp.sendRedirect("/mypage/address/list.do");
                } catch (IOException e) {
                    return "error/alertError";
                }
                return "shop/mypage/address/list";
            }
        }
    }
}
