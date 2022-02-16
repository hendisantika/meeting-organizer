package com.hendisantika.controller;

import com.hendisantika.service.MailService;
import com.hendisantika.service.UserService;
import com.hendisantika.util.ValidationErrorMessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.26
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    public final static String REGISTRATION_PAGE = "register",
            REDIRECT_TO_LOGIN_PAGE = "redirect:/login",
            REGISTRATION_CONFIRM_ENDPOINT = "/confirm",
            RESEND_TOKEN_PAGE = "resendToken";

    private final UserService userService;
    private final MailService mailService;
    private final ValidationErrorMessagesUtils errorsUtils;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegistrationController(UserService userService,
                                  MailService mailService,
                                  ValidationErrorMessagesUtils errorsUtils,
                                  ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.mailService = mailService;
        this.errorsUtils = errorsUtils;
        this.eventPublisher = eventPublisher;
    }
}
