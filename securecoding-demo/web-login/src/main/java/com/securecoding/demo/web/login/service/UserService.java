package com.securecoding.demo.web.login.service;

import com.securecoding.demo.web.login.dataaccess.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> getUserInfo(String userId);

    default Optional<User> getUserInfoByUsername(String username) {
        return Optional.empty();
    }
}
