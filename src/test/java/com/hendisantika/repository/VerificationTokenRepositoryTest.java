package com.hendisantika.repository;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
@DataJpaTest
public class VerificationTokenRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("password");
        user.setEmail("user@mail.com");

        testEntityManager.persistAndFlush(user);
    }

    @Test
    public void findByToken_recordFound_shouldReturnValidToken() {
        String token = UUID.randomUUID().toString();
        VerificationToken persistedToken = new VerificationToken(token, user);

        testEntityManager.persistAndFlush(persistedToken);
        VerificationToken foundToken = tokenRepository.findByToken(token);

        assertNotNull(foundToken);
        assertEquals(persistedToken.getToken(), foundToken.getToken());
        assertEquals(persistedToken.getId(), foundToken.getId());
        assertEquals(persistedToken.getUser(), foundToken.getUser());
        assertEquals(persistedToken.getExpirationTime(), foundToken.getExpirationTime());
    }

    @Test
    public void findByToken_recordNotFound_shouldReturnNull() {
        String token = UUID.randomUUID().toString();
        String otherToken = UUID.randomUUID().toString();
        VerificationToken persistedToken = new VerificationToken(token, user);

        testEntityManager.persistAndFlush(persistedToken);
        VerificationToken foundToken = tokenRepository.findByToken(otherToken);

        assertNull(foundToken);
    }

    @Test
    public void findByUser_recordFound_shouldReturnValidToken() {
        String token = UUID.randomUUID().toString();
        VerificationToken persistedToken = new VerificationToken(token, user);

        testEntityManager.persistAndFlush(persistedToken);
        VerificationToken foundToken = tokenRepository.findByUser(user);

        assertNotNull(foundToken);
        assertEquals(persistedToken.getToken(), foundToken.getToken());
        assertEquals(persistedToken.getId(), foundToken.getId());
        assertEquals(persistedToken.getUser(), foundToken.getUser());
        assertEquals(persistedToken.getExpirationTime(), foundToken.getExpirationTime());
    }

}
