package com.shop.webshop.web.api.web;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.service.ICartProductService;
import com.shop.webshop.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController(value = "CartProductApiOfClient")
public class CartProductApi {

    @Autowired
    private ICartProductService cartProductService;

    @PostMapping(value = "/cartProduct")
    public void addToCart(@RequestBody Long productId, @CookieValue(value = "cartId", required = false) Long cartId, @RequestParam(value = "q", required = false) Integer quantity, HttpServletResponse response) throws Exception {
        String username = null;
        if (SecurityUtils.getUserAuthorities().contains(CoreConstant.ROLE_USER)) {
            username = SecurityUtils.getPrincipal().getUsername();
        }
        if (quantity == null) quantity = 1;
        CartProductDTO cartProduct =  cartProductService.addToCart(productId, cartId, quantity, username, CoreConstant.ACTIVE_STATUS);
        if (!SecurityUtils.getUserAuthorities().contains(CoreConstant.ROLE_USER)) {
            Cookie cookie = new Cookie("cartId", cartProduct.getCart().getId().toString());
            response.addCookie(cookie);
        }
    }

    @PostMapping(value = "/cartProduct/edit/quantity")
    public void changeQuantity(@RequestBody CartProductDTO dto) throws Exception {
        cartProductService.changeQuantity(dto);
    }

    @DeleteMapping(value = "/cartProduct")
    public void deleteProductInCart(@RequestBody Long id) throws Exception {
        cartProductService.deleteProductInCart(id);
    }
}
