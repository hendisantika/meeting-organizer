package com.hendisantika.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.56
 * To change this template use File | Settings | File Templates.
 */
@Target({ANNOTATION_TYPE, TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPhone.CustomEmailValidator.class)
@Documented
public @interface ValidPhone {

    String message() default "Invalid phone format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CustomEmailValidator
            implements ConstraintValidator<ValidPhone, String> {

        private static final String PHONE_PATTERN = "[\\s\\d+-]+";
        private Pattern pattern;
        private Matcher matcher;

        @Override
        public void initialize(ValidPhone constraintAnnotation) {

        }

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            if (email == null || email.trim().equals("")) {
                return true;
            }

            pattern = Pattern.compile(PHONE_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
}
