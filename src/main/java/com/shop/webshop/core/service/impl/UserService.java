package com.shop.webshop.core.service.impl;

import com.shop.webshop.core.constant.CoreConstant;
import com.shop.webshop.core.dto.UserDTO;
import com.shop.webshop.core.entity.RoleEntity;
import com.shop.webshop.core.entity.UserEntity;
import com.shop.webshop.core.repository.UserRepository;
import com.shop.webshop.core.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(UserDTO userDTO) throws Exception {
        if (!checkUser(userDTO)) {
            throw new Exception("format_error");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleCode("USER");
        userEntity.setRole(roleEntity);
        userEntity.setStatus(CoreConstant.ACTIVE_STATUS);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    private boolean checkUser(UserDTO userDTO) throws Exception {
        String userName = userDTO.getUserName();
        String email = userDTO.getEmail();
        boolean valid = (userName != null) && userName.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{4,40}$");
        long userCount = userRepository.countAllByUserName(userName);
        if (userCount > 0) {
            throw new Exception("username_existed");
        }
        if (userDTO.getEmail() != null) {
            valid = valid && email.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
        }
        if (userDTO.getPhone() != null && userDTO.getPhone().length() < 10) return false;
        valid = valid && (userDTO.getPassword().equals(userDTO.getConfirmPassword()));
        return valid;
    }
}
