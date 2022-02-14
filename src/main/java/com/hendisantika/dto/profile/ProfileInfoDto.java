package com.hendisantika.dto.profile;

import com.hendisantika.domain.User;
import com.hendisantika.validation.ValidPhone;
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
 * Time: 08.16
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class ProfileInfoDto {

    @NotBlank
    @Size(min = 2)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    private String lastName;

    @ValidPhone
    private String phone;

    public ProfileInfoDto() {
    }

    public ProfileInfoDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
    }

    @Override
    public String toString() {
        return "ProfileInfoDto{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
