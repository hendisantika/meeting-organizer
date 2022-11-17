package com.hendisantika.validation;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/17/22
 * Time: 08:02
 * To change this template use File | Settings | File Templates.
 */
public class FieldsValueMatchTest {
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @FieldsValueMatch.List({
            @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail")
    })
    class TestDto {
        String email;
        String confirmEmail;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getConfirmEmail() {
            return confirmEmail;
        }

        public void setConfirmEmail(String confirmEmail) {
            this.confirmEmail = confirmEmail;
        }
    }
}
