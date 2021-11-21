package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.CategoryDTO;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll() throws Exception;
    List<CategoryDTO> findByProductId(Long productId);

    CategoryDTO save(CategoryDTO categoryDTO) throws IOException;

    void delete(Long[] ids);
}
