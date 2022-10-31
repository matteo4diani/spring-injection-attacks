package com.securecoding.demo.sql.injection.validator;

import com.securecoding.demo.web.login.validator.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SqlInputValidator implements InputValidator {

    private static final Logger LOG = LoggerFactory.getLogger(SqlInputValidator.class);

    @Override
    public void isValidUsername(String username) {
        if (username == null || "".equals(username)) {
            LOG.info("Username is not valid!");
            throw new IllegalArgumentException("Username is not valid!");
        }
    }

    @Override
    public void isValidPassword(String password) {
        if (password == null || "".equals(password)) {
            LOG.info("Password is not valid!");
            throw new IllegalArgumentException("Password is not valid!");
        }
    }

    @Override
    public void isValidUserId(String userId) {
        if (userId == null || "".equals(userId)) {
            LOG.info("User id is not valid!");
            throw new IllegalArgumentException("User id is not valid!");
        }
    }


}
