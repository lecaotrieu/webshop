package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.entity.CartProductEntity;
import org.springframework.beans.BeanUtils;

public class CartProductConvert {
    public static CartProductDTO toDTO(CartProductEntity entity) {
        if (entity == null) return null;
        CartProductDTO dto = new CartProductDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getProduct() != null) {
            dto.setProduct(ProductConvert.toDTO(entity.getProduct()));
        }
        if (entity.getCart() != null) {
            dto.setCart(CartConvert.toDTO(entity.getCart()));
        }
        return dto;
    }

    public static CartProductEntity toEntity(CartProductDTO dto) {
        if (dto == null) return null;
        CartProductEntity entity = new CartProductEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getProduct() != null) {
            entity.setProduct(ProductConvert.toEntity(dto.getProduct()));
        }
        if (dto.getCart() != null) {
            entity.setCart(CartConvert.toEntity(dto.getCart()));
        }
        return entity;
    }
}
