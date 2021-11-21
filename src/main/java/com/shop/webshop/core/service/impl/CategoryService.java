package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.convert.CategoryConvert;
import com.shop.webshop.core.dto.CategoryDTO;
import com.shop.webshop.core.entity.CategoryEntity;
import com.shop.webshop.core.repository.CategoryRepository;
import com.shop.webshop.core.service.ICategoryService;
import com.shop.webshop.core.utils.StringGlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() throws Exception {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> result = new ArrayList<>();
        categoryEntities.forEach(c -> result.add(CategoryConvert.toDTO(c)));
        return result;
    }

    @Override
    public List<CategoryDTO> findByProductId(Long productId) {
        List<CategoryEntity> categoryEntities = categoryRepository.findByProducts_id(productId);
        List<CategoryDTO> result = new ArrayList<>();
        categoryEntities.forEach(c -> result.add(CategoryConvert.toDTO(c)));
        return result;
    }

    @Autowired
    private StringGlobalUtils stringGlobalUtils;

    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) throws IOException {
        CategoryEntity entity;
        if (categoryDTO.getId() != null) {
            entity = categoryRepository.getOne(categoryDTO.getId());
            entity.setCategoryCode(categoryDTO.getCategoryCode());
            entity.setCategoryName(categoryDTO.getCategoryName());
        } else {
            entity = CategoryConvert.toEntity(categoryDTO);
        }
        if (entity.getCategoryCode() == null) {
            entity.setCategoryCode(stringGlobalUtils.covertToString(entity.getCategoryName()));
        }
        entity = categoryRepository.save(entity);
        return CategoryConvert.toDTO(entity);
    }

    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            CategoryEntity categoryEntity = categoryRepository.getOne(id);
            categoryEntity.setProducts(null);
            categoryRepository.save(categoryEntity);
            categoryRepository.deleteById(id);
        }
    }
}
