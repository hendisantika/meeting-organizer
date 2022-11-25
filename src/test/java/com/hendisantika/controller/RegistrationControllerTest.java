package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
import com.hendisantika.service.MailService;
import com.hendisantika.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/22
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = {MeetingOrganizerApplication.class, SecurityConfiguration.class, MeetingOrganizerConfiguration.class})
public class RegistrationControllerTest {
    private static final String REGISTRATION_URL = "/register";
    private static final String CONFIRM_TOKEN_URL = "/register/confirm";
    private static final String RESEND_TOKEN_URL = "/register/resendToken";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private RegistrationController registrationController;

    @MockBean
    private UserService usersService;

    @MockBean
    private MailService mailService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void registrationController_shouldNotBeNull() {
        assertNotNull(registrationController);
    }

    @Test
    public void displayRegistrationPage_getRequest_shouldReturnValidViewNameAndHasDtoInModel() throws Exception {
        mvc.perform(get(REGISTRATION_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(RegistrationController.REGISTRATION_PAGE))
                .andExpect(model().attributeExists("dto"));
    }
}
