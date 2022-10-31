package com.securecoding.demo.sql.injection.dataaccess.repository;

import com.securecoding.demo.sql.injection.dataaccess.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<List<UserRoleEntity>> findByUserId(BigInteger userId);
}
