package com.shop.webshop.core.utils;

import com.shop.webshop.core.constant.WebConstant;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class WebCommonUtil {
    public static void addRedirectMessage(HttpServletRequest request, Map<String, String> mapMessage, String message) {
        if (message != null && message.equals(WebConstant.REDIRECT_INSERT)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_INSERT));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (message != null && message.equals(WebConstant.REDIRECT_UPDATE)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_UPDATE));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (message != null && message.equals(WebConstant.REDIRECT_ERROR)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_ERROR));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        } else if (message != null && message.equals(WebConstant.REDIRECT_DELETE)) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_DELETE));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(message));
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        }
    }

    public static void addRedirectMessage(Model model, Map<String, String> mapMessage, String message) {
        if (message != null && message.equals(WebConstant.REDIRECT_INSERT)) {
            model.addAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_INSERT));
            model.addAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (message != null && message.equals(WebConstant.REDIRECT_UPDATE)) {
            model.addAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_UPDATE));
            model.addAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else if (message != null && message.equals(WebConstant.REDIRECT_ERROR)) {
            model.addAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_ERROR));
            model.addAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        } else if (message != null && message.equals(WebConstant.REDIRECT_DELETE)) {
            model.addAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_DELETE));
            model.addAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        } else {
            model.addAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(message));
            model.addAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
        }
    }
}
