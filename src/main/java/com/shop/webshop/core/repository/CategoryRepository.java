package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByProducts_id(Long productId);
}
