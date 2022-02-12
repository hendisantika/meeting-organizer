package com.hendisantika.dto;

import com.hendisantika.validation.FieldsValueMatch;
import com.hendisantika.validation.ValidEmail;
import com.hendisantika.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/02/22
 * Time: 21.52
 * To change this template use File | Settings | File Templates.
 */
@Data
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "FieldsValueMatch.passwordsNotMatch"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "FieldsValueMatch.emailsNotMatch"
        )
})
public class RegistrationFormDto {

    @NotBlank
    @Size(min = 2)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 100)
    @ValidEmail
    private String email;

    @NotBlank
    @Size(min = 3, max = 100)
    @ValidEmail
    private String confirmEmail;

    @NotBlank
    @Size(min = 8)
    @ValidPassword
    private String password;

    @NotBlank
    @Size(min = 8)
    @ValidPassword
    private String confirmPassword;
}
