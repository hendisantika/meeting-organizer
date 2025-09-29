package com.hendisantika.dto.profile;

import com.hendisantika.validation.FieldsValueMatch;
import com.hendisantika.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.17
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "FieldsValueMatch.emailsNotMatch"
        )
})
public class ProfileMailDto {

    @NotBlank
    @Size(min = 3, max = 100)
    @ValidEmail
    private String email;

    @NotBlank
    @Size(min = 3, max = 100)
    @ValidEmail
    private String confirmEmail;

    public ProfileMailDto() {
    }

    @Override
    public String toString() {
        return "ProfileMailDto{" +
                ", email='" + email + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                '}';
    }
}
