package com.thevolume360.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.thevolume360.domain.User;

@Component
public interface UserService extends UserDetailsService {
    void save(User user);

    User findByUserName(String username);

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    User getCurrentLoggedInUser();

    Long count();
}
