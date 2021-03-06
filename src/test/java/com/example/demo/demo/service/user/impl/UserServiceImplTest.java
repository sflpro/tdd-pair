package com.example.demo.demo.service.user.impl;

import com.example.demo.demo.domain.user.User;
import com.example.demo.demo.repository.user.UserRepository;
import com.example.demo.demo.service.user.UserService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by Arthur Asatryan.
 * Date: 12/20/17
 * Time: 5:54 PM
 */
@RunWith(EasyMockRunner.class)
public class UserServiceImplTest extends EasyMockSupport {

    //region Test subject and mocks
    @TestSubject
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;
    //endregion

    //region Test methods

    //region Initial
    @Test
    public void testUserServiceIsNotNull() {
        assertNotNull(userService);
    }

    @Test
    public void testUserRepositoryIsNotNull() {
        assertNotNull(userRepository);
    }
    //endregion

    //region create

    /**
     * With invalid arguments
     */
    @Test
    public void testCreate1() {
        // test data
        resetAll();
        final String email = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();
        // expectations
        replayAll();
        // test scenario
        try {
            userService.create(null, password);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            userService.create(email, null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    @Test
    public void testCreate2() {
        // test data
        resetAll();
        final String email = "vagho";
        final String password = "vaghinak";
        // expectations
        expect(userRepository.save(isA(User.class))).andAnswer(() -> (User) getCurrentArguments()[0]);
        replayAll();
        // test scenario
        final User result = userService.create(email, password);
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        verifyAll();
    }
    //endregion

    //region findByEmail

    /**
     * With invalid arguments
     */
    @Test
    public void testFindByEmail1() {
        // test data
        resetAll();
        // expectations
        replayAll();
        // test scenario
        try {
            userService.findByEmail(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * When user does not exists
     */
    @Test
    public void testFindByEmail2() {
        // test data
        resetAll();
        final String email = UUID.randomUUID().toString();
        // expectations
        expect(userRepository.findByEmail(email)).andReturn(null);
        replayAll();
        // test scenario
        final Optional<User> result = userService.findByEmail(email);
        assertNotNull(result);
        assertFalse(result.isPresent());
        verifyAll();
    }

    /**
     * When user exists
     */
    @Test
    public void testFindByEmail3() {
        // test data
        resetAll();
        final String email = UUID.randomUUID().toString();
        final User user = new User(email, UUID.randomUUID().toString());
        // expectations
        expect(userRepository.findByEmail(email)).andReturn(user);
        replayAll();
        // test scenario
        final Optional<User> result = userService.findByEmail(email);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verifyAll();
    }
    //endregion

    //endregion

}