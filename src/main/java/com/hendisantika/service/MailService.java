package com.hendisantika.service;

import org.springframework.mail.SimpleMailMessage;

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
public interface MailService {

    void sendEmail(SimpleMailMessage mailMessage);

    SimpleMailMessage prepareRegistrationMailMessage(String applicationUrl, String to, Locale locale);
}
