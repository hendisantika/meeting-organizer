package com.hendisantika.service;

import com.hendisantika.domain.User;
import com.hendisantika.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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

    @Test
    public void loadUserByUsername_userMaiIsInRepository_ShouldReturnUser() {
        String mail = "user@mail.com";
        User userFromRepository = new User();
        userFromRepository.setEmail(mail);

        when(userRepository.findByEmail(mail)).thenReturn(userFromRepository);
        UserDetails userDetails = userDetailsService.loadUserByUsername(mail);

        assertNotNull(userDetails);
        assertEquals(mail, userDetails.getUsername());
    }


    @Test
    public void loadUserByUsername_userMailNotInRepository_ShouldThrowException() {
        String mail = "user@mail.com";
        UsernameNotFoundException thrown = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            when(userRepository.findByEmail(mail)).thenReturn(null);
            userDetailsService.loadUserByUsername(mail);
        });
    }

}
