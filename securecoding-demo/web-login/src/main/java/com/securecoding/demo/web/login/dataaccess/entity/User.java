package com.securecoding.demo.web.login.dataaccess.entity;

import java.math.BigInteger;

public interface User {

    BigInteger getId();

    void setId(BigInteger id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);
}
