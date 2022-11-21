package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = {MeetingOrganizerApplication.class, SecurityConfiguration.class, MeetingOrganizerConfiguration.class})
public class HomeControllerTest {

    private static final String HOME_URL = "/home";

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private HomeController homeController;

    private MockMvc mvc;

}
