package com.securecoding.demo.sql.injection.dataaccess.entity;

import com.securecoding.demo.web.login.dataaccess.entity.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements User {

    @Id
    @NotNull
    private BigInteger id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;
}
