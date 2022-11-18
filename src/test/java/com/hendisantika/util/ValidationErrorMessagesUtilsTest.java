package com.hendisantika.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

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

    @Test
    public void errorMessagesForClassLevelValidations_givenErrorNotConnectedWithClassLevelValidation_ShouldReturnEmptyMap() {
        String[] codes = {TestUtils.createStringWithLength(5)};
        ObjectError error = new ObjectError("name", codes, new Object[]{}, "defaultMsg");

        assertEquals(Collections.emptyMap(), messagesUtils.errorMessagesForClassLevelValidations(List.of(error)));
    }

    @Test
    public void errorMessagesForClassLevelValidations_givenErrorConnectedWithClassLevelValidation_ShouldReturnValidMap() {
        String[] codes = {"FieldsValueMatch"};
        ObjectError error = new ObjectError("name", codes, new Object[]{},
                "FieldsValueMatch.propertyNotMatch");
        String expectedMessage = "Expected validation message";


        given(messageSource.getMessage("FieldsValueMatch.propertyNotMatch", null, Locale.getDefault()))
                .willReturn(expectedMessage);

        Map<String, String> messages = messagesUtils.errorMessagesForClassLevelValidations(List.of(error));
        String actualMessage = messages.get("propertyNotMatch");

        assertEquals(1, messages.size());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void extractMessageKeyNameFromErrorMessage_givenValidMessage_shouldReturnValidKey() {
        assertEquals("expected", messagesUtils.extractMessageKeyNameFromErrorMessage("annotationName.expected"));
    }

    @Test
    public void extractMessageKeyNameFromErrorMessage_givenInvalidFormat_shouldThrowException() {
        ArrayIndexOutOfBoundsException thrown = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            assertEquals("expected", messagesUtils.extractMessageKeyNameFromErrorMessage("annotationName"));
        });
    }
}
