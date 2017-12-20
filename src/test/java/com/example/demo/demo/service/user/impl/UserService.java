package com.example.demo.demo.service.user.impl;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 5:58 PM
 */
public interface UserService {

    User create(final String email, final String password);
}
