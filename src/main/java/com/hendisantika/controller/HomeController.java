package com.hendisantika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    public static final String HOME_PAGE = "homePage";

    @GetMapping
    public String displayHomePage() {
        return HOME_PAGE;
    }
}
