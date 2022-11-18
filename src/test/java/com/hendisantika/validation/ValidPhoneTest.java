package com.hendisantika.validation;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/18/22
 * Time: 08:53
 * To change this template use File | Settings | File Templates.
 */
public class ValidPhoneTest {
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    class TestDto {
        @ValidPhone(message = "Message")
        String phone;
    }

    @BeforeEach
    public void setup() {
        localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.afterPropertiesSet();
    }
}
