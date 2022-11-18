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

    @Test
    public void testValidPhone_givenEmptyString_shouldBeValidBecauseItMightBeEmpty() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto(""));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(0, constraintViolations.size());

        for (ConstraintViolation<TestDto> t : constraintViolations) {
            assertEquals(t.getMessage(), "Message");
        }
    }

    @Test
    public void testValidPassword_givenNumberWithLetters_shouldHasErrors() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("+48 123 abd"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }
}
