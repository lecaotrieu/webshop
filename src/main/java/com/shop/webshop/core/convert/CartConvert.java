package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.CartDTO;
import com.shop.webshop.core.entity.CartEntity;
import org.springframework.beans.BeanUtils;

public class CartConvert {
    public static CartDTO toDTO(CartEntity entity) {
        if (entity == null)
            return null;
        CartDTO dto = new CartDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getUser() != null)
            dto.setUser(UserConvert.toDTO(entity.getUser()));
        return dto;
    }

    public static CartEntity toEntity(CartDTO dto) {
        if (dto == null)
            return null;
        CartEntity entity = new CartEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getUser() != null)
            entity.setUser(UserConvert.toEntity(dto.getUser()));
        return entity;
    }
}
