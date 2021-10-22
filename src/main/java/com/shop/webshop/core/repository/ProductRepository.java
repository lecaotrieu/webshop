package com.shop.webshop.core.repository;

import com.shop.webshop.core.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("FROM ProductEntity e WHERE LOWER(e.productName) LIKE %?1% OR LOWER(e.productCode) LIKE %?1% AND e.status = ?2")
    List<ProductEntity> findAllByProperties(String search, Integer status, Pageable pageable);

    @Query("FROM ProductEntity e WHERE LOWER(e.productName) LIKE %?1% OR LOWER(e.productCode) LIKE %?1%")
    List<ProductEntity> findAllByProperties(String search, Pageable pageable);

    @Query("SELECT COUNT(e.id) FROM ProductEntity e WHERE LOWER(e.productName) LIKE %?1% OR LOWER(e.productCode) LIKE %?1%")
    int countAllByProperties(String search);

    @Query("SELECT COUNT(e.id) FROM ProductEntity e WHERE LOWER(e.productName) LIKE %?1% OR LOWER(e.productCode) LIKE %?1% AND e.status = ?2")
    int countAllByProperties(String search, Integer status);

    ProductEntity findByIdAndStatus(Long id, Integer status);
}
