package com.shop.webshop.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkout")
public class CheckoutEntity extends BaseEntity {

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private Long money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid")
    private CartEntity cart;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}
