package com.hendisantika.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.57
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ValidationErrorMessagesUtils implements MessageSourceAware {

    private static final List<String> CLASS_LEVEL_VALIDATION_ERROR_CODES = Arrays.asList("FieldsValueMatch");
    private MessageSource messageSource;

}
