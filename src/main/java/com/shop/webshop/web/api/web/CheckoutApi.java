package com.shop.webshop.web.api.web;

import com.shop.webshop.core.dto.CheckoutDTO;
import com.shop.webshop.core.dto.UserDTO;
import com.shop.webshop.core.service.ICheckoutService;
import com.shop.webshop.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "CheckoutApiOfClient")
public class CheckoutApi {

    @Autowired
    private ICheckoutService checkoutService;

    @PostMapping("/user/checkout")
    public void checkout(@RequestBody CheckoutDTO checkout) throws Exception{
        checkout.setUser(new UserDTO());
        checkout.getUser().setUserName(SecurityUtils.getPrincipal().getUsername());
        checkoutService.save(checkout);
    }
}
