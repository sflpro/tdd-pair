package com.example.demo.demo.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 6:01 PM
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    //region Dependencies
    //endregion

    //region Constructors
    public UserServiceImpl() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public User create(final String email, final String password) {
        Assert.notNull(email, "The email should not be null");
        Assert.notNull(password, "The password should not be null");
        LOGGER.debug("Creating user with email - {} and password - {}", email, password);
        final User user = userRepository.save(new User(email, password));
        LOGGER.debug("Successfully saved a user - {}", user);
        return user;
    }
    //endregion
}
