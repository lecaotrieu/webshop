package com.shop.webshop.core.dto;

import java.util.List;

public class CartDTO extends AbstractDTO{
    private Integer status;
    private UserDTO user;
    private List<CartProductDTO> cartProducts;
    private CheckoutDTO checkout;

    public CartDTO() {
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

}
