package com.example.demo.demo.service.user.impl;

import com.example.demo.demo.domain.user.User;
import com.example.demo.demo.repository.user.UserRepository;
import com.example.demo.demo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 6:01 PM
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    //region Dependencies
    @Autowired
    private UserRepository userRepository;
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

    @Override
    public Optional<User> findByEmail(final String email) {
        Assert.notNull(email, "The email should not be null");
        LOGGER.debug("Getting user for email - {}", email);
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
    //endregion
}
