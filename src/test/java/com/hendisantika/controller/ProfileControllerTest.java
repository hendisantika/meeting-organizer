package com.hendisantika.controller;

import com.hendisantika.MeetingOrganizerApplication;
import com.hendisantika.config.MeetingOrganizerConfiguration;
import com.hendisantika.config.SecurityConfiguration;
import com.hendisantika.domain.User;
import com.hendisantika.dto.profile.ProfileInfoDto;
import com.hendisantika.helper.TestHelper;
import com.hendisantika.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    @Test
    public void displayProfilePage_returnValidViewNameAndHasModelAttribute() throws Exception {
        mvc.perform(get(PROFILE_URL)
                        .with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(ProfileController.USER_ATTRIBUTE))
                .andExpect(view().name(ProfileController.PROFILE_PAGE));
    }

    @Test
    public void uploadProfileImage_shouldCallService() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig",
                MediaType.IMAGE_JPEG_VALUE, "image".getBytes());

        mvc.perform(
                        fileUpload(PROFILE_URL).file(file)
                                .with(csrf())
                                .with(user(TestHelper.sampleUser()))
                                .accept(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userService, times(1)).saveUserAndFlush(any(User.class));
    }

    @Test
    public void uploadProfileImage_fileIsEmpty_shouldRedirectAndShowMessage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig",
                MediaType.IMAGE_JPEG_VALUE, new byte[]{});

        mvc.perform(
                        fileUpload(PROFILE_URL).file(file)
                                .with(csrf())
                                .with(user(TestHelper.sampleUser()))
                                .accept(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"))
                .andExpect(flash().attributeCount(1));

        verify(userService, times(0)).saveUserAndFlush(any(User.class));
    }

    @Test
    public void uploadProfileImage_fileIsNotAnImage_shouldRedirectAndShowMessage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "orig",
                MediaType.APPLICATION_JSON_UTF8_VALUE, new byte[]{});

        mvc.perform(
                        fileUpload(PROFILE_URL).file(file)
                                .with(csrf())
                                .with(user(TestHelper.sampleUser()))
                                .accept(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"))
                .andExpect(flash().attributeCount(1));

        verify(userService, times(0)).saveUserAndFlush(any(User.class));
    }

    @Test
    public void displayEditProfilePage_shouldReturnValidViewNameAndHasModelAttributes() throws Exception {
        mvc.perform(
                        get(EDIT_PROFILE_URL).with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(
                        ProfileController.PROFILE_INFO_DTO,
                        ProfileController.PROFILE_MAIL_DTO,
                        ProfileController.PROFILE_PASSWORD_DTO
                ))
                .andExpect(view().name(ProfileController.EDIT_PROFILE_PAGE));
    }

    @Test
    public void processEditInfoForm_formValid_shouldCallService() throws Exception {

        mvc.perform(post(EDIT_PROFILE_URL)
                        .with(csrf())
                        .with(user(TestHelper.sampleUser()))
                        .accept(MediaType.TEXT_HTML)
                        .param("editInfo", "editInfo")
                        .param("firstName", "John")
                        .param("lastName", "Smith")
                        .param("phone", "+48 103 234 567"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("updateSuccessful"))
                .andExpect(view().name(ProfileController.EDIT_PROFILE_PAGE));

        verify(userService, times(1)).updateUserProfile(any(User.class), any(ProfileInfoDto.class));
    }

    @Test
    public void processEditInfoForm_formInvalid_shouldHasErrors() throws Exception {

        mvc.perform(post(EDIT_PROFILE_URL)
                        .with(csrf())
                        .with(user(TestHelper.sampleUser()))
                        .accept(MediaType.TEXT_HTML)
                        .param("editInfo", "editInfo")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("phone", "abc"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(5))
                .andExpect(view().name(ProfileController.EDIT_PROFILE_PAGE));

        verify(userService, times(0)).updateUserProfile(any(User.class), any(ProfileInfoDto.class));
    }

}
