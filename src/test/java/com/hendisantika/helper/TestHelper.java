package com.hendisantika.helper;

import com.hendisantika.domain.User;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/21/22
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class TestHelper {
    public static User sampleUser() {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("mail@domain.com");
        user.setPassword("pass");

        return user;
    }
}
