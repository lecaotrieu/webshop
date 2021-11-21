package com.shop.webshop.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity{

    @Column
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private UserEntity user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartProductEntity> cartProducts;

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER)
    private CheckoutEntity checkout;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public CheckoutEntity getCheckout() {
        return checkout;
    }

    public void setCheckout(CheckoutEntity checkout) {
        this.checkout = checkout;
    }
}
