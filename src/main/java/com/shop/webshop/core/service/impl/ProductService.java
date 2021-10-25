package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.convert.ProductConvert;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.entity.ProductEntity;
import com.shop.webshop.core.repository.ProductRepository;
import com.shop.webshop.core.service.IProductService;
import com.shop.webshop.core.utils.PagingUtils;
import com.shop.webshop.core.utils.SecurityUtils;
import com.shop.webshop.core.utils.StringGlobalUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PagingUtils pagingUtils;

    @Override
    public List<ProductDTO> findByProperties(String search, Integer page, Integer limit, String sortBy, String sortDesc) {
        Pageable pageable = pagingUtils.setPageable(page, limit, sortBy, sortDesc);
        List<ProductEntity> productEntities = productRepository.findAllByProperties(search, pageable);
        return convertToListDTO(productEntities);
    }

    public List<ProductDTO> findByProperties(String search, Integer status, Integer page, Integer limit, String sortBy, String sortDesc) {
        Pageable pageable = pagingUtils.setPageable(page, limit, sortBy, sortDesc);
        List<ProductEntity> productEntities = productRepository.findAllByProperties(search, status, pageable);
        return convertToListDTO(productEntities);
    }

    private List<ProductDTO> convertToListDTO(List<ProductEntity> entities) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        entities.stream().forEach(e -> {
            ProductDTO productDTO = ProductConvert.toDTO(e);
            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    @Override
    public int getTotalProduct(String search) {
        int result = productRepository.countAllByProperties(search);
        return result;
    }

    @Override
    public int getTotalProduct(String search, Integer status) {
        int result = productRepository.countAllByProperties(search, status);
        return result;
    }

    @Override
    public ProductDTO findById(Long id, Integer status) {
        ProductEntity productEntity = productRepository.findByIdAndStatus(id, status);
        ProductDTO productDTO = ProductConvert.toDTO(productEntity);
        return productDTO;
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = productRepository.getOne(id);
        ProductDTO productDTO = ProductConvert.toDTO(productEntity);
        return productDTO;
    }

    @Autowired
    private StringGlobalUtils stringGlobalUtils;

    @Transactional
    @Override
    public Long saveProduct(ProductDTO productDTO) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        if (productDTO.getId() != null) {
            productEntity = productRepository.getOne(productDTO.getId());
        }
        BeanUtils.copyProperties(productDTO, productEntity);
        if (productEntity.getId() == null) {
            productEntity.setCreatedDate(Calendar.getInstance().getTime());
            productEntity.setCreatedBy(SecurityUtils.getPrincipal().getUsername());
        } else {
            productEntity.setModifiedDate(Calendar.getInstance().getTime());
            productEntity.setModifiedBy(SecurityUtils.getPrincipal().getUsername());
            productEntity.setStatus(CoreConstant.ACTIVE_STATUS);
        }
        if (productEntity.getSale() == null) productEntity.setSale(0);
        productEntity.setStatus(productDTO.getStatus());
        productEntity.setProductCode(stringGlobalUtils.covertToString(productEntity.getProductName()));
        productEntity = productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Transactional
    @Override
    public void updateImage(Long id, String image) throws Exception {
        ProductEntity productEntity = productRepository.getOne(id);
        productEntity.setImage(image);
        productRepository.save(productEntity);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productRepository.delete(productEntity);
    }

    @Transactional
    @Override
    public void updateStatus(Long id, Integer status) {
        ProductEntity productEntity = productRepository.getOne(id);
        productEntity.setStatus(status);
        productRepository.save(productEntity);
    }
}
