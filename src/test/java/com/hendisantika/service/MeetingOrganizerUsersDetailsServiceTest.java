package com.hendisantika.service;

import com.hendisantika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/19/22
 * Time: 08:34
 * To change this template use File | Settings | File Templates.
 */
public class MeetingOrganizerUsersDetailsServiceTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @TestConfiguration
    static class UsersDetailsServiceTestContextConfiguration {

        @Bean
        public UserDetailsService userDetailsService() {
            return new MeetingOrganizerUsersDetailsService();
        }
    }

}
