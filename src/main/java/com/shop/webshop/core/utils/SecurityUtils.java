package com.shop.webshop.core.utils;

import com.shop.webshop.core.dto.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
    public static MyUser getPrincipal() {
        MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUser;
    }


    @SuppressWarnings("unchecked")
    public static List<String> getUserAuthorities() {
        List<String> result = new ArrayList<String>();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof MyUser) {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities) {
                result.add(authority.getAuthority());
            }
        }
        return result;
    }
}
