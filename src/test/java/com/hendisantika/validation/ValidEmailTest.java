package com.hendisantika.validation;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/17/22
 * Time: 08:05
 * To change this template use File | Settings | File Templates.
 */
public class ValidEmailTest {

    private LocalValidatorFactoryBean localValidatorFactoryBean;

    class TestDto {
        @ValidEmail(message = "Message")
        String email;
    }

}
