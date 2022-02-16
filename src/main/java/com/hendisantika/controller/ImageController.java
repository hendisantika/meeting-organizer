package com.hendisantika.controller;

import com.hendisantika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/profile/{id}/image")
public class ImageController {

    private final UserService userService;

    @Autowired
    public ImageController(UserService userService) {
        this.userService = userService;
    }
}
