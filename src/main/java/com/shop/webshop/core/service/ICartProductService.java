package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.CartProductDTO;

public interface ICartProductService {
    CartProductDTO addToCart(Long productId, Long cartId, Integer quantity, String username, Integer cartStatus) throws Exception;

    Long changeQuantity(CartProductDTO cartProduct);

    Long deleteProductInCart(Long id) throws Exception;

}
