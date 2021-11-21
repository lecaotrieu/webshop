package com.shop.webshop.web.controller.web;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.CartDTO;
import com.shop.webshop.core.service.ICartService;
import com.shop.webshop.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "CartControllerOfClient")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping(value = "/cart")
    public String cartDetail(@CookieValue(value = "cartId", required = false) Long cartId, Model model) {
        CartDTO cartDTO = new CartDTO();
        String username = null;
        if (SecurityUtils.getUserAuthorities().contains(CoreConstant.ROLE_USER)) {
            username = SecurityUtils.getPrincipal().getUsername();
            cartDTO = cartService.findByUsername(username);
            model.addAttribute("cart", cartDTO);
        }
        if (!SecurityUtils.getUserAuthorities().contains(CoreConstant.ROLE_USER)) {
            if (cartId != null) {
                cartDTO = cartService.findById(cartId);
                model.addAttribute("cart", cartDTO);
            }
        }
        return "views/web/cart/detail";
    }
}
