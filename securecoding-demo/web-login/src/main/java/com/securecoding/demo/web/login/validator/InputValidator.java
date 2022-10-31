package com.securecoding.demo.web.login.validator;

public interface InputValidator {

    void isValidUsername(String username);

    void isValidPassword(String password);

    void isValidUserId(String userId);

}
