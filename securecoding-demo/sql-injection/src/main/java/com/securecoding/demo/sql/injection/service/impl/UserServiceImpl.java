package com.securecoding.demo.sql.injection.service.impl;

import com.securecoding.demo.sql.injection.dataaccess.repository.UserRepository;
import com.securecoding.demo.web.login.dataaccess.entity.User;
import com.securecoding.demo.web.login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        LOG.info("Finding user with username: {} and password: {}", username, password);
        Optional<User> user = userRepository.findUserByUsernameAndPassword(username, password);
        user.ifPresent(value -> LOG.info("Returning user information for user {}", value.getUsername()));
        return user;
    }

    @Override
    public Optional<User> getUserInfo(String userId) {
        LOG.info("Finding user info with id: {}", userId);
        Optional<User> user = userRepository.findUserByUserId(userId);
        user.ifPresent(value -> LOG.info("Returning user information for user {}", value.getUsername()));
        return user;
    }

    @Override
    public Optional<User> getUserInfoByUsername(String username) {
        LOG.info("Finding user info with username: {}", username);
        Optional<User> user = userRepository.findUserByUsername(username);
        user.ifPresent(value -> LOG.info("Returning user information for user {}", value.getUsername()));
        return user;
    }
}
