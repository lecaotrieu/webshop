package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.entity.CartEntity;
import com.shop.webshop.core.entity.CartProductEntity;
import com.shop.webshop.core.entity.ProductEntity;
import com.shop.webshop.core.entity.UserEntity;
import com.shop.webshop.core.repository.CartProductRepository;
import com.shop.webshop.core.repository.CartRepository;
import com.shop.webshop.core.repository.ProductRepository;
import com.shop.webshop.core.repository.UserRepository;
import com.shop.webshop.core.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class CartProductService implements ICartProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @Transactional
    @Override
    public Long addToCart(Long productId, Long cartId, Integer quantity, String username) throws Exception {
        CartProductEntity cartProductEntity = new CartProductEntity();
        CartEntity cartEntity = null;
        if (username != null) {
            cartEntity = cartRepository.findAllByUser_userNameAndStatus(username, 1);
        } else if (cartId != null) {
            cartEntity = cartRepository.getOne(cartId);
        }
        if (cartEntity == null) {
            cartEntity = createNewCart(username);
        }
        boolean checkProductExist = true;
        if (cartEntity.getCartProducts() != null) {
            checkProductExist = cartEntity.getCartProducts().stream().filter(c -> c.getProduct().getId().equals(productId)).count() == 0;
        }
        if (checkProductExist) {
            cartProductEntity.setCart(cartEntity);
            cartProductEntity.setQuantity(quantity);
            ProductEntity productEntity = productRepository.findByIdAndStatus(productId, 1);
            cartProductEntity.setProduct(productEntity);
            Long totalMoney = productEntity.getPrice() * (100 - (productEntity.getSale() == null ? 0 : productEntity.getSale())) / 100 * quantity;
            cartProductEntity.setTotalMoney(totalMoney);
        } else {
            cartProductEntity = cartProductRepository.findAllByProduct_IdAndCart_Id(productId, cartEntity.getId());
            cartProductEntity.setQuantity(quantity);
            Long totalMoney = cartProductEntity.getProduct().getPrice() * (100 - (cartProductEntity.getProduct().getSale() == null ? 0 : cartProductEntity.getProduct().getSale())) / 100 * quantity;
            cartProductEntity.setTotalMoney(totalMoney);
        }
        cartProductRepository.save(cartProductEntity);
        return cartEntity.getId();
    }

    @Override
    public Long changeQuantity(CartProductDTO cartProduct) {
        CartProductEntity entity = cartProductRepository.getOne(cartProduct.getId());
        entity.setQuantity(cartProduct.getQuantity());
        Long totalMoney = entity.getProduct().getPrice() * (100 - (entity.getProduct().getSale() == null ? 0 : entity.getProduct().getSale())) / 100 * cartProduct.getQuantity();
        entity.setTotalMoney(totalMoney);
        cartProductRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Long deleteProductInCart(Long id) throws Exception {
        cartProductRepository.deleteById(id);
        return 1L;
    }

    @Autowired
    private UserRepository userRepository;

    private CartEntity createNewCart(String username) {
        CartEntity cartEntity = new CartEntity();
        if (username != null) {
            UserEntity userEntity = userRepository.getById(username);
            cartEntity.setUser(userEntity);
            cartEntity.setCreatedBy(username);
        }
        cartEntity.setStatus(1);
        cartEntity.setCreatedDate(Calendar.getInstance().getTime());
        return cartRepository.save(cartEntity);

    }
}
