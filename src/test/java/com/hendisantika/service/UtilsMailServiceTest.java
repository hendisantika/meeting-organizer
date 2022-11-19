package com.hendisantika.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/19/22
 * Time: 08:43
 * To change this template use File | Settings | File Templates.
 */
public class UtilsMailServiceTest {
    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private UtilsMailService mailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
