package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findByProperties(String search, Integer page, Integer limit, String sortBy, String sortDesc);

    List<ProductDTO> findByProperties(String search, Integer status, Integer page, Integer limit, String sortBy, String sortDesc);

    int getTotalProduct(String search);

    int getTotalProduct(String search, Integer status);

    ProductDTO findById(Long id, Integer status);

    ProductDTO findById(Long id);

    Long saveProduct(ProductDTO productDTO) throws Exception;

    void updateImage(Long id, String image) throws Exception;

    void deleteProduct(Long id) throws Exception;

    void updateStatus(Long id, Integer status);
}
