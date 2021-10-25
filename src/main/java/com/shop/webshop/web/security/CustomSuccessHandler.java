package com.shop.webshop.web.security;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.service.ICartService;
import com.shop.webshop.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ICartService cartService;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (WebUtils.getCookie(request, "cartId") != null) {
            String cookieCartId = WebUtils.getCookie(request, "cartId").getValue();
            Long cartId = Long.parseLong(cookieCartId);
            cartService.addUserToCart(cartId, SecurityUtils.getPrincipal().getUsername());
            // remove cookie cart
            Cookie cookie = new Cookie("cartId", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        HttpSession session = request.getSession();
        String targetUrl = "/";
        String redirectUrl = (String) session.getAttribute("url_prior_login");
        if (redirectUrl != null) {
            session.removeAttribute("url_prior_login");
            targetUrl = redirectUrl;
        }
        String urlAdminHome = determineTargetUrl(authentication);
        if (urlAdminHome != null) targetUrl = urlAdminHome;
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = null;
        if (SecurityUtils.getUserAuthorities().contains(CoreConstant.ROLE_ADMIN)) {
            url = "/admin/home-page";
        }
        return url;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
