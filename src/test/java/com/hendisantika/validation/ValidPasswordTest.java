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
 * Time: 08:50
 * To change this template use File | Settings | File Templates.
 */
public class ValidPasswordTest {
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    class TestDto {
        @ValidPassword(message = "Message")
        String password;
    }

    @BeforeEach
    public void setup() {
        localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.afterPropertiesSet();
    }

    @Test
    public void testValidPassword_givenToShortPassword_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("aZ1#"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());

        for (ConstraintViolation<TestDto> t : constraintViolations) {
            assertEquals(t.getMessage(), "Message");
        }
    }

    @Test
    public void testValidPassword_givenNullPassword_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(new TestDto());
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testValidPassword_givenNoSpecialCharacter_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("abcABC12"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }
}
