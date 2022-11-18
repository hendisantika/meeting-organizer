package com.hendisantika.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/18/22
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
public class ValidationErrorMessagesUtilsTest {
    @Autowired
    private ValidationErrorMessagesUtils messagesUtils;

    @MockBean
    private MessageSource messageSource;

    @Test
    public void errorMessagesForClassLevelValidations_givenEmptyList_ShouldReturnEmptyMap() {
        assertEquals(Collections.emptyMap(),
                messagesUtils.errorMessagesForClassLevelValidations(Collections.emptyList()));
    }
}
