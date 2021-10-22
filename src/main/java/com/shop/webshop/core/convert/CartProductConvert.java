package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.entity.CartProductEntity;
import org.springframework.beans.BeanUtils;

public class CartProductConvert {
    public static CartProductDTO toDTO(CartProductEntity entity) {
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
