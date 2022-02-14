package com.hendisantika.dto.profile;

import com.hendisantika.validation.FieldsValueMatch;
import com.hendisantika.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.18
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "FieldsValueMatch.passwordsNotMatch"
        )
})
public class ProfilePasswordDto {

    @NotBlank
    @Size(min = 8)
    @ValidPassword
    private String oldPassword;

    @NotBlank
    @Size(min = 8)
    @ValidPassword
    private String password;

    @NotBlank
    @Size(min = 8)
    @ValidPassword
    private String confirmPassword;

    public ProfilePasswordDto() {
    }

    @Override
    public String toString() {
        return "ProfilePasswordDto{" +
                ", oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
