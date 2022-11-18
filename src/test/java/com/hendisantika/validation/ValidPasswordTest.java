package com.hendisantika.validation;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/18/22
 * Time: 08:50
 * To change this template use File | Settings | File Templates.
 */
public class ValidPasswordTest {
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    class TestDto {
        @ValidPassword(message = "Message")
        String password;
    }
}
