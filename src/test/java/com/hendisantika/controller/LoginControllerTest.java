package com.hendisantika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/23/22
 * Time: 06:13
 * To change this template use File | Settings | File Templates.
 */
@WebMvcTest(controllers = LoginController.class, secure = false)
public class LoginControllerTest {

    private static final String LOGIN_URL = "/login";

    @Autowired
    private LoginController loginController;

    @Autowired
    private MockMvc mvc;

}
