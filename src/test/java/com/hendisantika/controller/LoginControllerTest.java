package com.hendisantika.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    private static final String LOGIN_URL = "/login";

    @Autowired
    private LoginController loginController;

    @Autowired
    private MockMvc mvc;

    @Test
    public void loginController_shouldNotBeNull() {
        assertNotNull(loginController);
    }

    @Test
    public void redirectToLogin_ShouldRedirectToLogin() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void displayLoginPage_GetRequest_ShouldReturnValidViewName() throws Exception {
        this.mvc.perform(get(LOGIN_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
