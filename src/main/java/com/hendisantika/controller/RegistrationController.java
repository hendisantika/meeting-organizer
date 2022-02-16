package com.hendisantika.controller;

import com.hendisantika.domain.User;
import com.hendisantika.domain.VerificationToken;
import com.hendisantika.dto.RegistrationFormDto;
import com.hendisantika.event.RegistrationCompleteEvent;
import com.hendisantika.service.MailService;
import com.hendisantika.service.UserService;
import com.hendisantika.util.ValidationErrorMessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

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

    /**
     * Display registration page and pass dto object to view
     *
     * @param model view model
     * @return name of the registration page
     */
    @GetMapping
    public String displayRegistrationPage(Model model) {
        model.addAttribute("dto", new RegistrationFormDto());
        return REGISTRATION_PAGE;
    }

    /**
     * Register new user if form has no errors and send email with authorization token
     *
     * @param dto                form's dto
     * @param model              view model
     * @param bindingResult      the result of a form validation
     * @param redirectAttributes attributes used only after redirection
     * @return name of the registration page if there were any error or login page after successful registration
     */
    @PostMapping
    public String processRegistrationForm(@Valid @ModelAttribute(name = "dto") RegistrationFormDto dto,
                                          BindingResult bindingResult,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {

        // Check for global errors - class level custom annotations
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()) {
                model.addAllAttributes(errorsUtils.errorMessagesForClassLevelValidations(bindingResult.getGlobalErrors()));
            }

            return REGISTRATION_PAGE;
        }

        if (userService.isEmailAlreadyTaken(dto.getEmail())) {
            model.addAttribute("emailAlreadyTaken", Boolean.TRUE);
            return REGISTRATION_PAGE;
        }

        User registeredUser = userService.registerUser(dto);
        String tokenConfirmationUrl = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();

        try {
            eventPublisher.publishEvent(new RegistrationCompleteEvent(registeredUser, tokenConfirmationUrl,
                    LocaleContextHolder.getLocale()));

            redirectAttributes.addFlashAttribute("registrationSuccessful", Boolean.TRUE);
        } catch (Exception e) {
            userService.rollbackUserRegistration(registeredUser);
            model.addAttribute("emailSendException", Boolean.TRUE);
            return REGISTRATION_PAGE;
        }

        return REDIRECT_TO_LOGIN_PAGE;
    }

    /**
     * Enable user's account if the token is valid and non-expired
     *
     * @param token              token from user's email
     * @param redirectAttributes
     * @return redirect to login page and show a message about operation result
     */
    @GetMapping("/confirm")
    public String confirmRegistration(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {

        VerificationToken verificationToken = userService.getVerificationToken(token);

        if (verificationToken == null) {
            redirectAttributes.addFlashAttribute("tokenNotFound", Boolean.TRUE);
            return REDIRECT_TO_LOGIN_PAGE;
        }

        if (verificationToken.isTokenExpired()) {
            redirectAttributes.addFlashAttribute("tokenExpired", Boolean.TRUE);
            return REDIRECT_TO_LOGIN_PAGE;
        }

        User userForToken = verificationToken.getUser();

        userForToken.setEnabled(true);
        userService.saveUserAndFlush(userForToken);

        redirectAttributes.addFlashAttribute("userEnabled", Boolean.TRUE);
        return REDIRECT_TO_LOGIN_PAGE;
    }

}
