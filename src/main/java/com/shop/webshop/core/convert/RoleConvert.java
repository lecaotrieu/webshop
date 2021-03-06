package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.RoleDTO;
import com.shop.webshop.core.entity.RoleEntity;
import org.springframework.beans.BeanUtils;

public class RoleConvert {
    public static RoleDTO toDTO(RoleEntity entity) {
        if (entity == null) return null;
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static RoleEntity toEntity(RoleDTO dto) {
        if (dto == null) return null;
        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
