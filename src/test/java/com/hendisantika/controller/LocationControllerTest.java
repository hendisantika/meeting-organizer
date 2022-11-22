package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
import com.hendisantika.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/23/22
 * Time: 06:09
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = {MeetingOrganizerApplication.class, SecurityConfiguration.class, MeetingOrganizerConfiguration.class})
public class LocationControllerTest {
    private static final String LOCATIONS_URL = "/location";
    private static final String ADD_LOCATION_URL = "/location/add";
    private static final String BROWSE_LOCATIONS_URL = "/location/browse";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private LocationController locationController;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void locationController_isNotNull() {
        assertNotNull(locationController);
    }

    @Test
    public void displayLocationsPage_shouldReturnValidViewName() throws Exception {
        mvc.perform(get(LOCATIONS_URL)
                        .with(user(TestHelper.sampleUser()))
                        .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(view().name(LocationController.LOCATIONS_PAGE))
                .andExpect(status().isOk());
    }

    @Test
    public void displayBrowseLocationsPage_shouldReturnValidViewName() throws Exception {
        mvc.perform(get(BROWSE_LOCATIONS_URL)
                        .with(user(TestHelper.sampleUser()))
                        .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(view().name(LocationController.BROWSE_LOCATIONS_PAGE))
                .andExpect(status().isOk());
    }
}
