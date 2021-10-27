package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.convert.CartConvert;
import com.shop.webshop.core.convert.CartProductConvert;
import com.shop.webshop.core.dto.CartDTO;
import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.entity.CartEntity;
import com.shop.webshop.core.entity.CartProductEntity;
import com.shop.webshop.core.entity.UserEntity;
import com.shop.webshop.core.repository.CartProductRepository;
import com.shop.webshop.core.repository.CartRepository;
import com.shop.webshop.core.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartDTO findById(Long id) {
        CartEntity cartEntity = cartRepository.getOne(id);
        if (cartEntity == null) return null;
        CartDTO cartDTO = CartConvert.toDTO(cartEntity);
        List<CartProductDTO> cartProductS = new ArrayList<>();
        cartEntity.getCartProducts().forEach(c -> {
            CartProductDTO cartProductDTO = CartProductConvert.toDTO(c);
            cartProductS.add(cartProductDTO);
        });
        cartDTO.setId(id);
        cartDTO.setCartProducts(cartProductS);
        return cartDTO;
    }

    @Override
    public CartDTO findByUsername(String username) {
        CartEntity cartEntity = cartRepository.findAllByUser_userNameAndStatus(username, CoreConstant.ACTIVE_STATUS);
        if (cartEntity == null) return null;
        CartDTO cartDTO = CartConvert.toDTO(cartEntity);
        List<CartProductDTO> cartProductS = new ArrayList<>();
        cartEntity.getCartProducts().forEach(c -> {
            CartProductDTO cartProductDTO = CartProductConvert.toDTO(c);
            cartProductS.add(cartProductDTO);
        });
        cartDTO.setCartProducts(cartProductS);
        return cartDTO;
    }

    @Autowired
    private CartProductRepository cartProductRepository;

    @Transactional
    @Override
    public void addUserToCart(Long id, String username) throws IOException {
        CartEntity oldCart = cartRepository.findAllByUser_userNameAndStatus(username, 1);
        CartEntity newCart = cartRepository.getOne(id);
        if (oldCart != null || newCart != null) {
            mergeCart(oldCart, newCart, username);
        }
    }

    private void mergeCart(CartEntity source, CartEntity target, String username) {
        if (target != null) {
            if (source != null) {
                if (source.getCartProducts().size() > 0) {
                    if (source.getUser() != null && source.getUser().getUserName() != null) {
                        username = source.getUser().getUserName();
                    }
                    // add product not exist
                    for (CartProductEntity entity : source.getCartProducts()) {
                        //check contains
                        boolean check = target.getCartProducts().stream()
                                .filter(c -> c.getProduct().getId().equals(entity.getProduct().getId()))
                                .findFirst().isPresent();
                        if (!check) {
                            entity.setCart(target);
                            cartProductRepository.save(entity);
                        } else {
                            cartProductRepository.delete(entity);
                        }
                    }
                    source.setStatus(0);
                    cartRepository.save(source);
                    cartRepository.delete(source);
                }
            }
            if (username != null) {
                target.setUser(new UserEntity());
                target.getUser().setUserName(username);
            }
            cartRepository.save(target);
        }
    }
}
