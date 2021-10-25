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
    public Long addUserToCart(Long id, String username) throws IOException {
        CartEntity oldCart = cartRepository.findAllByUser_userNameAndStatus(username, 1);
        CartEntity newCart = cartRepository.getOne(id);
        newCart = mergeCart(oldCart, newCart);
        return cartRepository.save(newCart).getId();
    }

    private CartEntity mergeCart(CartEntity source, CartEntity target) {
        if (source != null && source.getCartProducts().size() > 0) {
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
        }
        source.setStatus(0);
        cartRepository.save(source);
        String username = null;
        if (source.getUser() != null && source.getUser().getUserName() != null) {
            username = source.getUser().getUserName();
        } else if (target.getUser() != null && target.getUser().getUserName() != null) {
            username = target.getUser().getUserName();
        }
        if (username != null) {
            target.setUser(new UserEntity());
            target.getUser().setUserName(username);
        }
        cartRepository.delete(source);
        return target;
    }
}
