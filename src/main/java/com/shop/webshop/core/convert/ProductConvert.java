package com.shop.webshop.core.convert;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class ProductConvert {
    public static ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) return null;
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        if (dto.getImage() == null) {
            dto.setImageUrl("/template/image/no-image.png");
        } else{
            dto.setImageUrl("/" + CoreConstant.FOLDER_UPLOAD + "/" + CoreConstant.PRODUCT_IMAGE + "/" + dto.getId() + "/" + dto.getImage());
        }
        return dto;
    }

    public static ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) return null;
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
