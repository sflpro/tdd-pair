package com.example.demo.demo.service.user;

import com.example.demo.demo.domain.user.User;

import java.util.Optional;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 5:58 PM
 */
public interface UserService {

    User create(final String email, final String password);

    Optional<User> findByEmail(final String email);
}
