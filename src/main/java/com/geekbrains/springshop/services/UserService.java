package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.SystemUser;
import com.geekbrains.springshop.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    User findById(Long id);
    boolean save(SystemUser systemUser);
}
