package com.example.demo.demo.repository.user;

import com.example.demo.demo.domain.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 6:33 PM
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(final String email);
}
