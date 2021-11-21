package com.shop.webshop.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    @Column(name = "categorycode")
    private String categoryCode;

    @Column(name = "categoryname")
    private String categoryName;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
