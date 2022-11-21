package com.hendisantika.repository;

import com.hendisantika.domain.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
@DataJpaTest
public class AuthorityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void findByAuthority_noRecordsFound_shouldReturnNull() {
        Authority authority = new Authority("USER");
        String authorityNameToSearchFor = "ADMIN";

        testEntityManager.persistAndFlush(authority);
        Authority foundAuthority = authorityRepository.findByAuthority(authorityNameToSearchFor);

        assertNull(foundAuthority);
    }
}
