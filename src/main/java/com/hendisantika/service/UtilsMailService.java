package com.hendisantika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UtilsMailService implements MessageSourceAware, MailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailAccount;
    private MessageSource messageSource;

    @Autowired
    public UtilsMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public SimpleMailMessage prepareRegistrationMailMessage(String applicationUrl, String to, Locale locale) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String mailText = messageSource.getMessage("registrationPage.emailMessage", new Object[]{applicationUrl}, locale);
        String mailSubject = messageSource.getMessage("registrationPage.emailSubject", null, locale);

        mailMessage.setTo(to);
        mailMessage.setSubject(mailSubject);
        mailMessage.setText(mailText);
        mailMessage.setFrom(mailAccount);

        return mailMessage;
    }
}
