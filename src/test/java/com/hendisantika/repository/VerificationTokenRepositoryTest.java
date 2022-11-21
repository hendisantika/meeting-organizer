package com.hendisantika.repository;

import com.hendisantika.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
@DataJpaTest
public class VerificationTokenRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    private User user;

}
