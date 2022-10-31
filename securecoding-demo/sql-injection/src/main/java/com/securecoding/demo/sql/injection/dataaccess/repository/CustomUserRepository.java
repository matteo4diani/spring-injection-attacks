package com.securecoding.demo.sql.injection.dataaccess.repository;

import com.securecoding.demo.web.login.dataaccess.entity.User;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUserId(String userId);
}
