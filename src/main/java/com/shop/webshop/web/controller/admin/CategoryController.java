package com.shop.webshop.web.controller.admin;

import com.shop.webshop.core.constant.WebConstant;
import com.shop.webshop.core.dto.CategoryDTO;
import com.shop.webshop.core.service.ICategoryService;
import com.shop.webshop.core.utils.WebCommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller(value = "CategoryControllerOfAdmin")
public class CategoryController {
    ResourceBundle bundle = ResourceBundle.getBundle("i18n/message");

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/admin/category/list")
    public String categoryList(Model model, @RequestParam(value = "message", required = false) String message) {
        try {
            List<CategoryDTO> categories = categoryService.findAll();
            model.addAttribute(WebConstant.ITEMS, categories);
            if (message != null) {
                WebCommonUtil.addRedirectMessage(model, getMapMessage(), message);
            }
            return "views/admin/category/list";
        } catch (Exception e) {
            return "redirect:/404-page";
        }
    }

    private Map<String, String> getMapMessage() {
        Map<String, String> mapValue = new HashMap<String, String>();
        mapValue.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        mapValue.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.category.message.add.success"));
        mapValue.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.category.message.delete.success"));
        mapValue.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.category.message.update.success"));
        return mapValue;
    }

}
