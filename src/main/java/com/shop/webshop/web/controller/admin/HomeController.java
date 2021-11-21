package com.shop.webshop.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "HomeControllerOfAdmin")
public class HomeController {
    @GetMapping(value = "/admin/home-page")
    public String adminPage() {
        return "views/admin/home";
    }
}
