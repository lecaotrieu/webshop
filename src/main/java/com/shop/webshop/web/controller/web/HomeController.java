package com.shop.webshop.web.controller.web;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.CommonDTO;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "HomeControllerOfClient")
public class HomeController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = {"/home-page/{page}", "/home-page", "/"})
    public String homePage(Model model, CommonDTO commonDTO, @PathVariable(value = "page", required = false) Integer page) {
        commonDTO.setLimit(8);
        if (page != null) {
            commonDTO.setPage(page);
        }
        List<ProductDTO> products = productService.findByProperties(commonDTO.getSearch(), CoreConstant.ACTIVE_STATUS, commonDTO.getPage(), commonDTO.getLimit(), null, null);
        model.addAttribute("products", products);
        int totalItems = productService.getTotalProduct(commonDTO.getSearch(), CoreConstant.ACTIVE_STATUS);
        commonDTO.setTotalItems(totalItems);
        commonDTO.setTotalPage((int) Math.ceil((double) commonDTO.getTotalItems() / commonDTO.getLimit()));
        model.addAttribute("common", commonDTO);
        return "views/index";
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        if (referrer != null) {
            if(!referrer.contains("login") && !referrer.contains("register")&& !referrer.contains("admin")){
                request.getSession().setAttribute("url_prior_login", referrer);
            }
        }
        return "views/login";
    }
}
