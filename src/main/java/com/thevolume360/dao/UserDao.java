package com.thevolume360.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
