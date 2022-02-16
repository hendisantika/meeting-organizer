package com.hendisantika.event;

import com.hendisantika.domain.User;
import com.hendisantika.service.MailService;
import com.hendisantika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = (User) event.getSource();
        String url = event.getTokenConfirmationUrl();
        String token = UUID.randomUUID().toString();

        url += RegistrationController.REGISTRATION_CONFIRM_ENDPOINT;
        url += ("?token=" + token);

        userService.createVerificationToken(user, token);

        SimpleMailMessage mailMessage = mailService.prepareRegistrationMailMessage(url,
                user.getEmail(), event.getLocale());
        mailSender.send(mailMessage);
    }
}
