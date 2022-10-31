package com.securecoding.demo.sql.injection.service;

import com.securecoding.demo.sql.injection.dataaccess.entity.UserRoleEntity;
import com.securecoding.demo.sql.injection.dataaccess.repository.UserRepository;
import com.securecoding.demo.sql.injection.dataaccess.repository.UserRoleRepository;
import com.securecoding.demo.web.login.dataaccess.entity.User;
import com.securecoding.demo.web.login.security.WebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SqlInjectionUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(SqlInjectionUserDetailsService.class);

    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public SqlInjectionUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        User user = userOptional.get();

        Optional<List<UserRoleEntity>> userRoles = userRoleRepository.findByUserId(user.getId());

        List<SimpleGrantedAuthority> grantedAuthorities = userRoles
                .map(userRoleList ->
                        userRoleList
                                .stream()
                                .map(result ->
                                        new SimpleGrantedAuthority(ROLE_PREFIX + result.getRole()))
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        if (grantedAuthorities.isEmpty()) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        LOG.info("Loaded user by username {}", username);

        return WebLoginUser
                .builder()
                .userId(user.getId())
                .username(user.getUsername())
                .authorities(grantedAuthorities)
                .build();
    }
}
