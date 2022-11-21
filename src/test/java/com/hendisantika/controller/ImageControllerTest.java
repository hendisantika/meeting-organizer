package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
import com.hendisantika.domain.User;
import com.hendisantika.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = {MeetingOrganizerApplication.class, SecurityConfiguration.class, MeetingOrganizerConfiguration.class})
public class ImageControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ImageController imageController;

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
    public void imageController_isNotNull() {
        assertNotNull(imageController);
    }

    @Test
    public void getUsersProfileImage_userNotFound_emptyResponse() throws Exception {
        when(userService.findOne(any(Long.class))).thenReturn(null);

        MvcResult mvcResult = mvc.perform(get("/profile/{id}/image", 1)
                        .with(user(new User())))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(0, mvcResult.getResponse().getContentLength());
    }
}
