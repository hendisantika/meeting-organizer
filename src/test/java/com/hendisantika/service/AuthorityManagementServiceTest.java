package com.hendisantika.service;

import com.hendisantika.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/19/22
 * Time: 08:31
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityManagementServiceTest {
    @Autowired
    private AuthorityService authorityService;
    @MockBean
    private AuthorityRepository authorityRepository;

    @TestConfiguration
    static class AuthorityManagementServiceTestContextConfiguration {

        @Bean
        public AuthorityService authorityService() {
            return new AuthorityManagementService();
        }
    }
}
