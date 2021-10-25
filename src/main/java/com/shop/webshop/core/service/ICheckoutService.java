package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.CheckoutDTO;

import java.util.List;

public interface ICheckoutService {

    void save(CheckoutDTO checkout) throws Exception;

    List<CheckoutDTO> findByProperties(String username, String search, int page, int limit, String sortBy, String sortDesc);

    Integer getTotalCheckout(String username, String search);

    CheckoutDTO findByIdAndUsername(Long id, String username);
}
