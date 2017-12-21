package com.example.demo.demo.service.user;

import com.example.demo.demo.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Arthur Asatryan.
 * Date: 12/21/17
 * Time: 5:22 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    //region Dependencies
    @Autowired
    private UserService userService;
    //endregion

    //region Test methods
    @Test
    public void testCrate() {
        // given
        final String email = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();
        // when
        final User result = userService.create(email, password);
        // then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
    }

    @Test
    public void testFindByEmail() {
        // given
        final User user = createAndPersistUser();
        // when
        final Optional<User> result = userService.findByEmail(user.getEmail());
        // then
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
    //endregion

    //region Utility methods
    private User createAndPersistUser() {
        return userService.create(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
    //endregion

}
