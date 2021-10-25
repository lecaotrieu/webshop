package com.shop.webshop.core.dto;

import java.util.Date;
import java.util.List;

public class CartDTO {
    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private Integer status;
    private UserDTO user;
    private List<CartProductDTO> cartProducts;
    private CheckoutDTO checkout;

    public CartDTO() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CartProductDTO> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDTO> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public CheckoutDTO getCheckout() {
        return checkout;
    }

    public void setCheckout(CheckoutDTO checkout) {
        this.checkout = checkout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
