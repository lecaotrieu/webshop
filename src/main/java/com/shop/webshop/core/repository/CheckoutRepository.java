package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.CheckoutEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> {
    @Query("SELECT c FROM CheckoutEntity c WHERE (LOWER(c.address) LIKE %?2% OR c.phone LIKE %?2%) AND c.user.userName = ?1")
    List<CheckoutEntity> findAllByProperties(String username, String search, Pageable pageable);

    @Query("SELECT count(c) FROM CheckoutEntity c WHERE (LOWER(c.address) LIKE %?2% OR c.phone LIKE %?2%) AND c.user.userName = ?1")
    Integer countAllByProperties(String username, String search);

    CheckoutEntity findByIdAndUser_userName(Long id, String username);
}