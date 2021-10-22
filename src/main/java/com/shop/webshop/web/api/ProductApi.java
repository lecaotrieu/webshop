package com.shop.webshop.web.api;

import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    @Autowired
    private IProductService productService;

    @DeleteMapping(value = "/admin/product")
    public String deleteProduct(@RequestBody Long id) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            return "/admin/product/list?delete_error";
        }
        return "/admin/product/list?delete_success";
    }

    @PutMapping(value = "/admin/product/status")
    public void updateStatus(@RequestBody ProductDTO product) {
        productService.updateStatus(product.getId(), product.getStatus());
    }

}
