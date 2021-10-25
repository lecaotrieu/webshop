package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.CheckoutDTO;
import com.shop.webshop.core.entity.CheckoutEntity;
import org.springframework.beans.BeanUtils;

public class CheckoutConvert {
    public static CheckoutDTO toDTO(CheckoutEntity entity) {
        if (entity == null) return null;
        CheckoutDTO dto = new CheckoutDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getUser() != null) {
            dto.setUser(UserConvert.toDTO(entity.getUser()));
        }
        if (entity.getCart() != null) {
            dto.setCart(CartConvert.toDTO(entity.getCart()));
        }
        return dto;
    }

    public static CheckoutEntity toEntity(CheckoutDTO dto) {
        if (dto == null) return null;
        CheckoutEntity entity = new CheckoutEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getUser() != null) {
            entity.setUser(UserConvert.toEntity(dto.getUser()));
        }
        if (dto.getCart() != null) {
            entity.setCart(CartConvert.toEntity(dto.getCart()));
        }
        return entity;
    }

}
