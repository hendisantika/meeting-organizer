package com.hendisantika.repository;

import com.hendisantika.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void countAllByEmail_noRecordsFound_shouldReturn0() {
        String userMail = "user@mail.com",
                searchedMail = "other@mail.com";
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("password");
        user.setEmail(userMail);

        testEntityManager.persistAndFlush(user);

        Long usersWithSameEmail = userRepository.countAllByEmailIgnoreCase(searchedMail);

        assertEquals(Long.valueOf(0), usersWithSameEmail);
    }

    @Test
    public void countAllByEmail_recordsFound_shouldReturnValidNumberOfRecords() {
        String userMail = "user@mail.com";
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("password");
        user.setEmail(userMail);

        testEntityManager.persistAndFlush(user);


        Long usersWithSameEmail = userRepository.countAllByEmailIgnoreCase(userMail);
        assertEquals(Long.valueOf(1), usersWithSameEmail);
    }

    @Test
    public void findByEmail_noRecordFound_shouldReturnNull() {
        String userMail = "user@mail.com",
                searchedMail = "other@mail.com";
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("password");
        user.setEmail(userMail);

        testEntityManager.persistAndFlush(user);

        User userFound = userRepository.findByEmail(searchedMail);

        assertNull(userFound);
    }


}
