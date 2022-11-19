package com.hendisantika.service;

import com.hendisantika.repository.UserRepository;
import com.hendisantika.repository.VerificationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
