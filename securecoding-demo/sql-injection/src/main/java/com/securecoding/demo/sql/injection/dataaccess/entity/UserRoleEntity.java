package com.securecoding.demo.sql.injection.dataaccess.entity;

import com.securecoding.demo.web.login.dataaccess.entity.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name = "user_roles")
@Data
public class UserRoleEntity implements UserRole {

    @Id
    @NotNull
    private BigInteger id;

    @NotNull
    private BigInteger userId;

    @NotNull
    private String role;
}
