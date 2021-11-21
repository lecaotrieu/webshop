package com.shop.webshop.web.controller.admin;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.CategoryDTO;
import com.shop.webshop.core.dto.CommonDTO;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.service.ICategoryService;
import com.shop.webshop.core.service.IProductService;
import com.shop.webshop.core.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller(value = "ProductControllerOfAdmin")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/admin/product/list")
    public String productList(Model model, CommonDTO common) {
        List<ProductDTO> productDTOS = productService.findByProperties(common.getSearch(), common.getPage(), common.getLimit(), common.getSortBy(), common.getSortDesc());
        model.addAttribute("productList", productDTOS);
        int totalItems = productService.getTotalProduct(common.getSearch());
        common.setTotalItems(totalItems);
        common.setTotalPage((int) Math.ceil((double) common.getTotalItems() / common.getLimit()));
        model.addAttribute("common", common);
        return "views/admin/product/list";
    }

    @GetMapping(value = "/admin/product/edit")
    public String editProductPage(Model model, @RequestParam(required = false, name = "id") Long id) {
        ProductDTO productDTO = new ProductDTO();
        if (id != null) {
            productDTO = productService.findById(id);
            List<CategoryDTO> productCategories = categoryService.findByProductId(id);
            try {
                List<CategoryDTO> categoryDTOS = categoryService.findAll();
                categoryDTOS.stream().filter(c -> productCategories.stream().anyMatch(pc -> pc.getId().equals(c.getId()))).forEach(c -> c.setChecked(true));
                model.addAttribute("categories", categoryDTOS);
            } catch (Exception e) {
            }
        }

        model.addAttribute("product", productDTO);
        return "views/admin/product/edit";
    }

    @Autowired
    private IUploadFileService uploadFileService;

    @PostMapping(value = "/admin/product/edit")
    public String editProduct(ProductDTO productDTO, HttpServletRequest request) {
        try {
            Long productId = productService.saveProduct(productDTO);
            if (productDTO.getFileImage().getOriginalFilename() != null && !productDTO.getFileImage().getOriginalFilename().equals("")) {
                String fileName = getFileName(productDTO.getFileImage(), productId);
                String uploadDir = CoreConstant.FOLDER_UPLOAD + File.separator + CoreConstant.PRODUCT_IMAGE + File.separator + productId;
                String name = uploadFileService.uploadFileInProject(uploadDir, fileName, productDTO.getFileImage(), request);
                productService.updateImage(productId, name);
            }
        } catch (Exception e) {
            return "redirect:/admin/product/edit?error&id=" + productDTO.getId();
        }
        if (productDTO.getId() != null) {
            return "redirect:/admin/product/edit?update_success&id=" + productDTO.getId();
        } else {
            return "redirect:/admin/product/list?insert_success";
        }
    }


    private String getFileName(MultipartFile file, Long id) {
        String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
        fileName = "product_" + id + fileName.substring(fileName.length() - 4);
        return fileName;
    }
}
