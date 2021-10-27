package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.CartDTO;

import java.io.IOException;

public interface ICartService {
    CartDTO findById(Long id);

    CartDTO findByUsername(String username);

    void addUserToCart(Long id, String username) throws IOException;

}
