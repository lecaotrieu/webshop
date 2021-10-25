package com.shop.webshop.core.convert;

import com.shop.webshop.core.dto.UserDTO;
import com.shop.webshop.core.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserConvert {
    public static UserDTO toDTO(UserEntity entity) {
        if (entity == null) return null;
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getRole() != null) {
            dto.setRole(RoleConvert.toDTO(entity.getRole()));
        }
        return dto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        if (dto == null) return null;
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getRole() != null) {
            entity.setRole(RoleConvert.toEntity(dto.getRole()));
        }
        return entity;
    }
}
