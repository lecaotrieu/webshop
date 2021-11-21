package com.shop.webshop.web.controller.web;

import com.shop.webshop.core.dto.UserDTO;
import com.shop.webshop.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "UserControllerOfClient")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/register")
    public String registerUser(UserDTO userDTO) {
        try{
            userService.registerUser(userDTO);
        } catch (Exception e) {
            switch (e.getMessage()){
                case "format_error":
                    return "redirect:/register?format_error";
                case "username_existed":
                    return "redirect:/register?username_existed";
            }
        }
        return "redirect:/login?register_success";
    }

    @GetMapping(value = "/register")
    public String registerPage(UserDTO userDTO) {
        return "views/register";
    }

    @GetMapping(value = "/dang-xuat")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home-page";
    }
}
