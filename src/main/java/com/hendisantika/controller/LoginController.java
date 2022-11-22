package com.hendisantika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/23/22
 * Time: 06:14
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {

    public static final String LOGIN_PAGE = "login";

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
