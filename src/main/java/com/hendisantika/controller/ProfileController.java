package com.hendisantika.controller;

import com.hendisantika.service.UserService;
import com.hendisantika.util.ValidationErrorMessagesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/23/22
 * Time: 06:18
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    public static final String PROFILE_PAGE = "profile/profilePage";
    public static final String EDIT_PROFILE_PAGE = "profile/editProfilePage";
    public static final String REDIRECT_TO_PROFILE = "redirect:/profile";

    public static final String USER_ATTRIBUTE = "user";
    public static final String PROFILE_INFO_DTO = "infoDto";
    public static final String PROFILE_MAIL_DTO = "mailDto";
    public static final String PROFILE_PASSWORD_DTO = "passwordDto";

    private final UserService userService;
    private final ValidationErrorMessagesUtils errorsUtils;

    @GetMapping
    public String displayProfilePage() {
        return PROFILE_PAGE;
    }
}
