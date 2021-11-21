package com.shop.webshop.web.controller.web;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.CommonDTO;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.service.IProductService;
import com.shop.webshop.core.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller(value = "ProductControllerOfClient")
public class ProductController {

    @Autowired
    private IProductService productService;



    @GetMapping(value = "/product/detail/{id}")
    public String productDetail(Model model, @PathVariable("id") Long id) {
        ProductDTO productDTO = productService.findById(id, CoreConstant.ACTIVE_STATUS);
        model.addAttribute("product", productDTO);
        return "views/web/product/detail";
    }
}
