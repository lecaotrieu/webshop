package com.shop.webshop.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{
    @Column(name = "productcode")
    private String productCode;

    @Column(name = "productname")
    private String productName;

    @Column
    private Long price;

    @Column
    private Integer sale;

    @Column
    private String image;

    @Column
    private String description;

    @Column
    private String store;

    @Column
    private Integer status;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProducts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
