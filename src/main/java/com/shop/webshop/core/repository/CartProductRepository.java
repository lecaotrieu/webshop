package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    CartProductEntity findAllByProduct_IdAndCart_Id(Long productId, Long cartId);
}
