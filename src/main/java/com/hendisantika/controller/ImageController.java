package com.hendisantika.controller;

import com.hendisantika.domain.User;
import com.hendisantika.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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

    @GetMapping
    public void getUsersProfileImage(@PathVariable long id, HttpServletResponse response) throws IOException {
        User user = userService.findOne(id);
        byte[] image = null;

        if (user != null) {
            image = user.getProfilePicture();
        }

        if (image != null) {
            response.getOutputStream().write(userService.findOne(id).getProfilePicture());
            response.setContentType("image/jpg");
        }
    }
}
