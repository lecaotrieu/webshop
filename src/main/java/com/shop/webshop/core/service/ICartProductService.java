package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.CartProductDTO;

public interface ICartProductService {
    Long addToCart(Long productId, Long cartId, Integer quantity, String username) throws Exception;

    Long changeQuantity(CartProductDTO cartProduct);

    Long deleteProductInCart(Long id) throws Exception;
}
