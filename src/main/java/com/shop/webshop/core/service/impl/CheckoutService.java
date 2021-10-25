package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.convert.CartProductConvert;
import com.shop.webshop.core.convert.CheckoutConvert;
import com.shop.webshop.core.convert.ProductConvert;
import com.shop.webshop.core.dto.CartProductDTO;
import com.shop.webshop.core.dto.CheckoutDTO;
import com.shop.webshop.core.dto.ProductDTO;
import com.shop.webshop.core.entity.CartEntity;
import com.shop.webshop.core.entity.CheckoutEntity;
import com.shop.webshop.core.entity.ProductEntity;
import com.shop.webshop.core.repository.CartRepository;
import com.shop.webshop.core.repository.CheckoutRepository;
import com.shop.webshop.core.service.ICheckoutService;
import com.shop.webshop.core.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CheckoutService implements ICheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public void save(CheckoutDTO checkout) throws Exception {
        CartEntity cart = cartRepository.getById(checkout.getCart().getId());
        Long totalMoney = cart.getCartProducts().stream().mapToLong(c -> c.getTotalMoney()).sum();
        CheckoutEntity entity = CheckoutConvert.toEntity(checkout);
        entity.setCreatedDate(Calendar.getInstance().getTime());
        entity.setMoney(totalMoney);
        checkoutRepository.save(entity);
        cart.setStatus(0);
        cartRepository.save(cart);
    }

    @Autowired
    private PagingUtils pagingUtils;

    @Override
    public List<CheckoutDTO> findByProperties(String username, String search, int page, int limit, String sortBy, String sortDesc) {
        Pageable pageable = pagingUtils.setPageable(page, limit, sortBy, sortDesc);
        List<CheckoutEntity> checkoutEntities = checkoutRepository.findAllByProperties(username, search, pageable);
        return convertToListDTO(checkoutEntities);
    }

    private List<CheckoutDTO> convertToListDTO(List<CheckoutEntity> checkoutEntities) {
        List<CheckoutDTO> checkoutDTOS = new ArrayList<>();
        checkoutEntities.stream().forEach(e -> {
            CheckoutDTO checkoutDTO = CheckoutConvert.toDTO(e);
            checkoutDTOS.add(checkoutDTO);
        });
        return checkoutDTOS;
    }

    @Override
    public Integer getTotalCheckout(String username, String search) {
        int result = checkoutRepository.countAllByProperties(username, search);
        return result;
    }

    @Override
    public CheckoutDTO findByIdAndUsername(Long id, String username) {
        CheckoutEntity entity = checkoutRepository.findByIdAndUser_userName(id, username);
        CheckoutDTO checkoutDTO = CheckoutConvert.toDTO(entity);
        List<CartProductDTO> cartProductDTOS = new ArrayList<>();
        entity.getCart().getCartProducts().forEach(c -> {
            CartProductDTO cartProductDTO = CartProductConvert.toDTO(c);
            cartProductDTOS.add(cartProductDTO);
        });
        checkoutDTO.getCart().setCartProducts(cartProductDTOS);
        return checkoutDTO;
    }
}
