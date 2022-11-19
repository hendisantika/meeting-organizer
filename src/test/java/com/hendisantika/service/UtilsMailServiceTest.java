package com.hendisantika.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
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

    @Test
    public void sendEmail_shouldCallMailSender() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailService.sendEmail(mailMessage);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void prepareRegistrationMailMessage_ShouldCreateCorrectMessage() {
        String url = "http://localhost:8080";
        String to = "user@mail.com";
        Locale locale = Locale.getDefault();
        MessageSource messageSource = Mockito.mock(MessageSource.class);

        when(messageSource.getMessage(anyString(), isNull(), any(Locale.class))).thenReturn("subject");
        when(messageSource.getMessage(anyString(), isNotNull(), any(Locale.class))).thenReturn("text");
        mailService.setMessageSource(messageSource);

        SimpleMailMessage actualMail = mailService.prepareRegistrationMailMessage(url, to, locale);

        assertNotNull(actualMail);
        assertEquals(to, actualMail.getTo()[0]);
        assertEquals("subject", actualMail.getSubject());
        assertEquals("text", actualMail.getText());
    }

}
