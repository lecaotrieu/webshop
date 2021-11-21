package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findAllByIdAndUser_userName(Long cartId, String username);

    CartEntity findAllByUser_userName(String username);

    CartEntity findAllByUser_userNameAndStatus(String username, Integer i);
}
