package com.hendisantika.event;

import com.hendisantika.service.MailService;
import com.hendisantika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.25
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RegistrationListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    private final JavaMailSender mailSender;

    private final MailService mailService;

    @Autowired
    public RegistrationListener(UserService userService, JavaMailSender mailSender, MailService mailService) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.mailService = mailService;
    }

}
