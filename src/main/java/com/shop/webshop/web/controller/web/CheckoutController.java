package com.shop.webshop.web.controller.web;

import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.dto.CheckoutDTO;
import com.shop.webshop.core.dto.CommonDTO;
import com.shop.webshop.core.service.ICartProductService;
import com.shop.webshop.core.service.ICartService;
import com.shop.webshop.core.service.ICheckoutService;
import com.shop.webshop.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller(value = "CheckoutControllerOfClient")
public class CheckoutController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartProductService cartProductService;

    @Autowired
    private ICheckoutService checkoutService;

    @GetMapping(value = {"/user/checkout/p-{productId}"})
    public String checkout(Model model, @PathVariable(value = "productId", required = false) Long productId, @RequestParam(value = "quantity", required = false) Integer quantity) {
        if (productId != null) {
            String username = SecurityUtils.getPrincipal().getUsername();
            try {
                CartProductDTO cartProductDTO = cartProductService.addToCart(productId, null, quantity == null ? 1 : quantity, username, 2);
                model.addAttribute("productDetail", cartProductDTO);
            } catch (Exception e) {

            }
        }
        return "views/web/checkout/edit";
    }

    @GetMapping(value = "/user/checkout/list")
    public String checkoutList(Model model, CommonDTO common) {
        String username = SecurityUtils.getPrincipal().getUsername();
        List<CheckoutDTO> checkouts = checkoutService.findByProperties(username, common.getSearch(), common.getPage(), common.getLimit(), common.getSortBy(), common.getSortDesc());
        model.addAttribute("checkouts", checkouts);
        int totalItems = checkoutService.getTotalCheckout(username, common.getSearch());
        common.setTotalItems(totalItems);
        common.setTotalPage((int) Math.ceil((double) common.getTotalItems() / common.getLimit()));
        model.addAttribute("common", common);
        return "views/web/checkout/list";
    }

    @GetMapping(value = "/user/checkout/detail-{id}")
    public String checkoutDetail(Model model, @PathVariable("id") Long id) {
        String username = SecurityUtils.getPrincipal().getUsername();
        CheckoutDTO checkout = checkoutService.findByIdAndUsername(id, username);
        model.addAttribute("checkout", checkout);
        return "views/web/checkout/detail";
    }
}
