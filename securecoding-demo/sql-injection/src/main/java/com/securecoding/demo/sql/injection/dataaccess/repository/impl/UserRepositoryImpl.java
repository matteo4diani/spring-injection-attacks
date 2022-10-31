package com.securecoding.demo.sql.injection.dataaccess.repository.impl;

import com.securecoding.demo.sql.injection.dataaccess.entity.UserEntity;
import com.securecoding.demo.sql.injection.dataaccess.repository.CustomUserRepository;
import com.securecoding.demo.web.login.dataaccess.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private static final String FIND_USER_BY_USERNAME_PASSWORD =
            "select id, username, password, name, surname " +
                    "from users u " +
                    "where u.username = '%s' " +
                    "and u.password = '%s'";

    private static final String FIND_USER_BY_USERNAME =
            "select id, username, password, name, surname " +
                    "from users u " +
                    "where u.username = '%s'";

    private static final String FIND_USER_BY_USERID =
            "select id, username, password, name, surname " +
                    "from users u " +
                    "where u.id = %s";

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return queryDatabase(FIND_USER_BY_USERNAME_PASSWORD, username, password);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return queryDatabase(FIND_USER_BY_USERNAME, username);
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return queryDatabase(FIND_USER_BY_USERID, userId);
    }

    private Optional<User> queryDatabase(String query, Object... params) {
        String formattedQuery = String.format(query, params);
        Query nativeQuery = this.em.createNativeQuery(formattedQuery);
        LOG.debug("Querying user with {}", formattedQuery);
        List<Object[]> result = nativeQuery.getResultList();
        if (result == null || result.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(mapResultToUser(result.get(0)));
    }

    private User mapResultToUser(Object[] result) {
        UserEntity user = new UserEntity();
        user.setId(new BigInteger(result[0].toString()));
        user.setUsername(result[1].toString());
        user.setPassword(result[2].toString());
        user.setName(result[3].toString());
        user.setSurname(result[4].toString());
        return user;
    }

}
