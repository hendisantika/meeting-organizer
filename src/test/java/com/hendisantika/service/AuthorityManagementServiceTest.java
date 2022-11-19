package com.hendisantika.service;

import com.hendisantika.domain.Authority;
import com.hendisantika.repository.AuthorityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

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

    @Test
    public void findAuthorityByNameCreateAuthorityIfNotFound_authorityFound_shouldReturnAuthorityFromDatabase() {
        String authorityUser = "USER";

        when(authorityRepository.findByAuthority(authorityUser)).thenReturn(new Authority(authorityUser));
        Authority foundAuthority = authorityService.findAuthorityByNameCreateAuthorityIfNotFound(authorityUser);

        assertNotNull(foundAuthority);
        assertEquals(authorityUser, foundAuthority.getAuthority());
    }
}
