package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserNameAndStatus(String userName, Integer status);
    long countAllByUserName(String userName);
}
