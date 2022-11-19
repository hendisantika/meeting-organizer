package com.hendisantika.service;

import com.hendisantika.repository.UserRepository;
import com.hendisantika.repository.VerificationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
