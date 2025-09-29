package com.hendisantika.validation;

import com.hendisantika.service.LocationService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.53
 * To change this template use File | Settings | File Templates.
 */
@Target({ANNOTATION_TYPE, TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLocationName.CustomLocationNameValidator.class)
@Documented
public @interface UniqueLocationName {

    String message() default "This location name is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CustomLocationNameValidator
            implements ConstraintValidator<UniqueLocationName, String> {

        private final LocationService locationService;

        public CustomLocationNameValidator(LocationService locationService) {
            this.locationService = locationService;
        }

        @Override
        public void initialize(UniqueLocationName constraintAnnotation) {
        }

        @Override
        public boolean isValid(String locationName, ConstraintValidatorContext context) {
            return !locationService.existsByNameIgnoreCase(locationName);
        }
    }
}
