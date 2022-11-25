package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
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

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/22
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = {MeetingOrganizerApplication.class, SecurityConfiguration.class, MeetingOrganizerConfiguration.class})
public class ProfileControllerTest {
    private static final String PROFILE_URL = "/profile";
    private static final String EDIT_PROFILE_URL = "/profile/edit";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ProfileController profileController;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void profileController_isNotNull() {
        assertNotNull(profileController);
    }
}
