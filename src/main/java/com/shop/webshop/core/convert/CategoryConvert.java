package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.CategoryDTO;
import com.shop.webshop.core.entity.CategoryEntity;
import org.springframework.beans.BeanUtils;

public class CategoryConvert {
    public static CategoryDTO toDTO(CategoryEntity entity) {
        if (entity == null) return null;
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static CategoryEntity toEntity(CategoryDTO dto) {
        if (dto == null) return null;
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
