package com.hendisantika.validation;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @BeforeEach
    public void setup() {
        localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.afterPropertiesSet();
    }

    @Test
    public void testFieldsValueMatch_givenDifferentValues_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("invalid.mail", "valid.mail"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }
}
