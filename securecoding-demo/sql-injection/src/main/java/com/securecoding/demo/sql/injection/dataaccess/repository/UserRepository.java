package com.securecoding.demo.sql.injection.dataaccess.repository;

import com.securecoding.demo.sql.injection.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
}
