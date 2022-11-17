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
 * Time: 08:05
 * To change this template use File | Settings | File Templates.
 */
public class ValidEmailTest {

    private LocalValidatorFactoryBean localValidatorFactoryBean;

    class TestDto {
        @ValidEmail(message = "Message")
        String email;
    }

    @BeforeEach
    public void setup() {
        localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.afterPropertiesSet();
    }

    @Test
    public void testValidEmail_givenEmailWithoutAt_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("invalid.mail"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());

        for (ConstraintViolation<TestDto> t : constraintViolations) {
            assertEquals(t.getMessage(), "Message");
        }
    }

    @Test
    public void testValidEmail_givenEmailWithNothingAfterAt_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("invalid.mail@"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testValidEmail_givenTooShortDomain_ShouldBeInvalid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("invalid.mail@t"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(1, constraintViolations.size());
    }


    @Test
    public void testValidEmail_givenValidEmail_ShouldBeValid() {
        Set<ConstraintViolation<TestDto>> constraintViolations =
                localValidatorFactoryBean.validate(prepareDto("valid.mail@domain.com"));
        constraintViolations = new HashSet<>(constraintViolations);

        assertEquals(0, constraintViolations.size());
    }
}
