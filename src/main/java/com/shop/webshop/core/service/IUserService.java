package com.shop.webshop.core.service;

import com.shop.webshop.core.dto.UserDTO;

public interface IUserService {
    void registerUser(UserDTO userDTO) throws Exception;
}
