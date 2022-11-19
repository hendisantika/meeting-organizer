package com.hendisantika.service;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import com.hendisantika.repository.UserRepository;
import com.hendisantika.repository.VerificationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/19/22
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */
public class UsersManagementServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityService authorityService;

    @Mock
    private VerificationTokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersManagementService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isEmailAlreadyTaken_mailNotInRepository_ShouldReturnFalse() {
        String mail = "user@mail.com";

        when(userRepository.countAllByEmailIgnoreCase(mail)).thenReturn(0L);
        boolean isMailTaken = userService.isEmailAlreadyTaken(mail);

        assertFalse(isMailTaken);
    }

    @Test
    public void isEmailAlreadyTaken_mailInRepository_ShouldReturnTrue() {
        String mail = "user@mail.com";

        when(userRepository.countAllByEmailIgnoreCase(mail)).thenReturn(1L);
        boolean isMailTaken = userService.isEmailAlreadyTaken(mail);

        assertTrue(isMailTaken);
    }

    @Test
    public void getVerificationToken_tokenFound_ShouldReturnToken() {
        String token = "token";

        when(tokenRepository.findByToken(token)).thenReturn(new VerificationToken(token, new User()));
        VerificationToken tokenFromRepository = userService.getVerificationToken(token);

        assertNotNull(tokenFromRepository);
        assertEquals(token, tokenFromRepository.getToken());
    }

    @Test
    public void getVerificationToken_tokenNotFound_ShouldReturnNull() {
        when(tokenRepository.findByToken(anyString())).thenReturn(null);
        VerificationToken tokenFromRepository = userService.getVerificationToken("token");

        assertNull(tokenFromRepository);
    }

    @Test
    public void createVerificationToken_shouldCallTokenRepository() {
        String token = "token";
        User user = new User();

        userService.createVerificationToken(user, token);

        verify(tokenRepository, times(1)).saveAndFlush(any(VerificationToken.class));
    }

    @Test
    public void saveUser_shouldReturnSavedUser() {
        User user = new User();
        user.setEmail("user@mail.com");

        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUserAndFlush(user);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void generateNewVerificationToken_shouldCreateValidToken() {
        User user = new User();
        user.setId(1L);
        user.setEmail("user@mail.com");
        VerificationToken oldToken = new VerificationToken("token", user);
        oldToken.setExpirationTime(LocalDateTime.now());
        VerificationToken newToken = new VerificationToken("tokenUpdated", user);
        newToken.setExpirationTime(oldToken.getExpirationTime().plusHours(24));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(tokenRepository.findByUser(user)).thenReturn(oldToken);
        when(tokenRepository.saveAndFlush(any(VerificationToken.class))).thenReturn(newToken);

        VerificationToken updatedToken = userService.generateNewVerificationToken("user@mail.com");

        assertNotNull(updatedToken);
        assertEquals(newToken.getExpirationTime(), updatedToken.getExpirationTime());
        assertEquals(newToken.getToken(), updatedToken.getToken());
        assertEquals(newToken.getUser(), updatedToken.getUser());
    }
}
